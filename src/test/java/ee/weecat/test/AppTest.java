package ee.weecat.test;

import org.junit.Test;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

/**
 * Created by wangkun23 on 2019/9/20.
 */
public class AppTest {

    private final char UNDERLINE = '_';

    @Test
    public void test() throws ParserConfigurationException, TransformerException {
        // 创建解析器工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        Document document = db.newDocument();
        // 不显示standalone="no"
        //document.setXmlStandalone(true);
        String tables = "aws_active_data,"
                + "aws_answer,"
                + "aws_answer_comments,"
                + "aws_answer_thanks,"
                + "aws_answer_uninterested,"
                + "aws_answer_vote,"
                + "aws_approval,"
                + "aws_article,"
                + "aws_article_comments,"
                + "aws_article_vote,"
                + "aws_attach,"
                + "aws_ban_ip,"
                + "aws_category,"
                + "aws_column,"
                + "aws_column_focus,"
                + "aws_draft,"
                + "aws_edm_task,"
                + "aws_edm_taskdata,"
                + "aws_edm_unsubscription,"
                + "aws_edm_userdata,"
                + "aws_edm_usergroup,"
                + "aws_education_experience,"
                + "aws_favorite,"
                + "aws_favorite_tag,"
                + "aws_feature,"
                + "aws_feature_topic,"
                + "aws_geo_location,"
                + "aws_help_chapter,"
                + "aws_inbox,"
                + "aws_inbox_dialog,"
                + "aws_integral_log,"
                + "aws_invitation,"
                + "aws_jobs,"
                + "aws_mail_queue,"
                + "aws_menu,"
                + "aws_nav_menu,"
                + "aws_notes,"
                + "aws_notification,"
                + "aws_notification_data,"
                + "aws_order_detail,"
                + "aws_pages,"
                + "aws_payment,"
                + "aws_plugins,"
                + "aws_posts_index,"
                + "aws_product_order,"
                + "aws_project,"
                + "aws_project_like,"
                + "aws_project_product,"
                + "aws_question,"
                + "aws_question_comments,"
                + "aws_question_complain,"
                + "aws_question_focus,"
                + "aws_question_invite,"
                + "aws_question_thanks,"
                + "aws_question_uninterested,"
                + "aws_received_email,"
                + "aws_receiving_email_config,"
                + "aws_redirect,"
                + "aws_related_links,"
                + "aws_related_topic,"
                + "aws_report,"
                + "aws_reputation_category,"
                + "aws_reputation_topic,"
                + "aws_school,"
                + "aws_search_cache,"
                + "aws_sessions,"
                + "aws_sysaccount,"
                + "aws_system_setting,"
                + "aws_ticket,"
                + "aws_ticket_invite,"
                + "aws_ticket_log,"
                + "aws_ticket_reply,"
                + "aws_topic,"
                + "aws_topic_focus,"
                + "aws_topic_merge,"
                + "aws_topic_relation,"
                + "aws_user_account,"
                + "aws_user_action_history,"
                + "aws_user_action_history_data,"
                + "aws_user_action_history_fresh,"
                + "aws_user_follow,"
                + "aws_user_refund,"
                + "aws_user_withdraw,"
                + "aws_users,"
                + "aws_users_attrib,"
                + "aws_users_facebook,"
                + "aws_users_google,"
                + "aws_users_group,"
                + "aws_users_notification_setting,"
                + "aws_users_online,"
                + "aws_users_qq,"
                + "aws_users_sina,"
                + "aws_users_twitter,"
                + "aws_users_ucenter,"
                + "aws_users_weixin,"
                + "aws_verify_apply,"
                + "aws_weibo_msg,"
                + "aws_weixin_accounts,"
                + "aws_weixin_login,"
                + "aws_weixin_message,"
                + "aws_weixin_msg,"
                + "aws_weixin_qr_code,"
                + "aws_weixin_reply_rule,"
                + "aws_weixin_third_party_api,"
                + "aws_work_experience";

        Element rootElement = document.createElement("tables");
        for (String name : tables.split(",")) {
            Element tableElement = document.createElement("table");
            // 为book节点添加属性
            String domain = underlineToCamel(name.substring(3));
            tableElement.setAttribute("schema", "weecat");
            tableElement.setAttribute("tableName", name);
            tableElement.setAttribute("domainObjectName", domain);
            tableElement.setAttribute("enableCountByExample", "false");
            tableElement.setAttribute("enableUpdateByExample", "false");
            tableElement.setAttribute("enableDeleteByExample", "false");
            tableElement.setAttribute("enableSelectByExample", "false");
            tableElement.setAttribute("selectByExampleQueryId", "false");

            rootElement.appendChild(tableElement);
        }
        document.appendChild(rootElement);
        // 创建TransformerFactory对象
        TransformerFactory tff = TransformerFactory.newInstance();
        // 创建 Transformer对象
        Transformer tf = tff.newTransformer();

        // 输出内容是否使用换行
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        // 创建xml文件并写入内容
        tf.transform(new DOMSource(document), new StreamResult(new File("book1.xml")));
        System.out.println("生成book1.xml成功");
    }


    /**
     * 下划线 转 驼峰
     *
     * @param param
     * @return
     */
    public String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = Character.toLowerCase(param.charAt(i));
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
