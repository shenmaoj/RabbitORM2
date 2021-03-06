package rabbit.open.orm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import rabbit.open.orm.shard.ShardingPolicy;

/**
 * 标记表实体
 * @author	肖乾斌
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.TYPE)
public @interface Entity {
	
	//表名
	public String value();
	
	//是否参与ddl
	public boolean ddlIgnore() default false;
	
	//分表策略实现
	public Class<? extends ShardingPolicy> policy() default ShardingPolicy.class;
	
	//设置关心的字段，用于查询过滤
	public String[] concern() default {};
	
}
