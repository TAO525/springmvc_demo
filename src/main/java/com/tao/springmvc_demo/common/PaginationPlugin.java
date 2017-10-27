package com.tao.springmvc_demo.common;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Properties;

/**
 * @Author TAO
 * @Date 2017/10/27 11:21
 */
public class PaginationPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 生成mapping 添加自定义sql
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();// 数据库表名
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        XmlElement parentElement = document.getRootElement();

        // 添加sql——where
        XmlElement sql = new XmlElement("sql");
        sql.addAttribute(new Attribute("id", "sql_where"));
        XmlElement where = new XmlElement("where");
        StringBuilder sb = new StringBuilder();
        for (IntrospectedColumn introspectedColumn : introspectedTable.getNonPrimaryKeyColumns()) {
            XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
            sb.setLength(0);
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != null"); //$NON-NLS-1$
            isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
            where.addElement(isNotNullElement);

            sb.setLength(0);
            sb.append(" and ");
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            sb.append(" = "); //$NON-NLS-1$
            sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
            isNotNullElement.addElement(new TextElement(sb.toString()));
        }
        sql.addElement(where);
        parentElement.addElement(sql);

        //添加getList
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "getList"));
        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        select.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        select.addElement(new TextElement(" select * from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()));

        XmlElement include = new XmlElement("include");
        include.addAttribute(new Attribute("refid", "sql_where"));

        select.addElement(include);
        parentElement.addElement(select);

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    /**
     * 生成dao
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        /*FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("BaseDao<" + introspectedTable.getBaseRecordType()  + ">");
        FullyQualifiedJavaType imp = new FullyQualifiedJavaType("common.BaseDao");
        interfaze.addSuperInterface(fqjt);// 添加 extends BaseDao<User>
        interfaze.addImportedType(imp);// 添加import common.BaseDao;
        interfaze.getMethods().clear();*/
        // 获取实体类
        FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        //类名
        System.out.println(entityType.getShortName());
        //创建方法
        Method method = new Method("getList");
        Parameter parameter = new Parameter(entityType,entityType.getShortName());
        method.addParameter(parameter);
        FullyQualifiedJavaType newListInstance = FullyQualifiedJavaType.getNewListInstance();
        newListInstance.addTypeArgument(entityType);
        method.setReturnType(newListInstance);
        interfaze.addMethod(method);
        interfaze.addImportedType(FullyQualifiedJavaType.getNewListInstance());
        super.clientGenerated(interfaze,topLevelClass,introspectedTable);
        return true;
    }

    private CommentGeneratorConfiguration commentCfg;
    @Override
    public void setContext(Context context) {
        super.setContext(context);
        // 设置默认的注释生成器
        commentCfg = new CommentGeneratorConfiguration();
        commentCfg.setConfigurationType(GenCommentGenerator.class.getCanonicalName());
        context.setCommentGeneratorConfiguration(commentCfg);
        // 支持oracle获取注释#114
        context.getJdbcConnectionConfiguration().addProperty("remarksReporting", "true");
    }

}
