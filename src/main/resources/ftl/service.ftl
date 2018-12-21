package ${package}.${directory};

import ${package}.entity.${class};

 public interface ${class}Service {

	void save(${class} ${classObj});

	<#--public ${classPre}Dao() {-->
		<#--super();-->
		<#--this.setTableName("${ tableName }");-->
		<#--this.setKeyName("id");-->
		<#--this.setVoClassName(${classPre}VO.class);-->
	<#--}-->

	<#--public ${classPre}Dao(String table, String key) {-->
		<#--super(table, key);-->
		<#--this.setVoClassName(${classPre}VO.class);-->
	<#--}-->

	<#--public ${classPre}Dao(String table, String key, String dsJDNI) {-->
		<#--super(table, key, dsJDNI);-->
		<#--this.setVoClassName(${classPre}VO.class);-->
	<#--}-->

	<#--public ${classPre}Dao(String table, String key, String dsJDNI, Class voClass) {-->
		<#--super(table, key, dsJDNI, voClass);-->
	<#--}-->
}