package rabbit.open.orm.dml;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;

import rabbit.open.orm.annotation.Column;
import rabbit.open.orm.dml.meta.FieldMetaData;
import rabbit.open.orm.dml.meta.MetaData;
import rabbit.open.orm.exception.InvalidGroupByFieldException;
import rabbit.open.orm.pool.SessionFactory;


/**
 * <b>@description 动态字段查询 </b>
 * @param <T>
 */
public class DynamicQuery<T> extends Query<T> {

	private HashSet<String> groupBy = new HashSet<>();
	
	public DynamicQuery(SessionFactory fatory, Class<T> clz) {
		this(fatory, null, clz);
	}
	
	public DynamicQuery(SessionFactory fatory, T filterData, Class<T> clz) {
		super(fatory, filterData, clz);
		forbiddenDynamic = false;
	}

	@Override
	protected void createGroupBySql() {
		if (!groupBy.isEmpty()) {
			sql.append(" GROUP BY ");
			String tableNameAlias = getAliasByTableName(getMetaData().getTableName());
			for (String f : groupBy) {
				String columName = getColumnNameByFieldName(f);
				sql.append(tableNameAlias).append(".").append(columName).append(", ");
			}
			sql.deleteCharAt(sql.lastIndexOf(","));
		}
	}

	/**
	 * <b>@description 根据类字段名获取数据库字段名 </b>
	 * @param f
	 * @return
	 */
	private String getColumnNameByFieldName(String f) {
		Map<String, FieldMetaData> cachedFieldsMetas = MetaData
		        .getCachedFieldsMetas(getEntityClz());
		if (cachedFieldsMetas.containsKey(f)) {
			return getColumnName(cachedFieldsMetas.get(f).getColumn());
		}
		return "";
	}
	
	@Override
	public AbstractQuery<T> groupBy(String... fields) {
		for (String field : fields) {
			Field f = checkField(getEntityClz(), field);
			if (f.getAnnotation(Column.class).dynamic()) {
				throw new InvalidGroupByFieldException(field);
			}
			groupBy.add(field);
		}
		return this;
	}
}
