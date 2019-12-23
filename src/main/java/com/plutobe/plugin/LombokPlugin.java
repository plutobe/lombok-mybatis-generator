package com.plutobe.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * mybaits lombok 插件自定义
 *
 * @author plutobe@qq.com
 * @date 2019-10-12
 */
public class LombokPlugin extends PluginAdapter {

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
        topLevelClass.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable());
        topLevelClass.addJavaDocLine(" * @author plutobe@qq.com");
        topLevelClass.addJavaDocLine(" * @date " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        topLevelClass.addJavaDocLine(" */");
        topLevelClass.addImportedType("lombok.Setter");
        topLevelClass.addImportedType("lombok.Getter");
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return true;
    }


    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
        field.addJavaDocLine(" */");
        field.addAnnotation("@Setter");
        field.addAnnotation("@Getter");
        return true;
    }


    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType mapperType = new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper");
        interfaze.addImportedType(mapperType);
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable());
        interfaze.addJavaDocLine(" * " + introspectedTable.getRemarks());
        interfaze.addJavaDocLine(" * @author plutobe@qq.com");
        interfaze.addJavaDocLine(" * @date " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        interfaze.addJavaDocLine(" */");
        interfaze.addAnnotation("@Mapper");
        return true;
    }

    public boolean validate(List<String> warnings) {
        return true;
    }

}
