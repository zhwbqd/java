-- Application
INSERT INTO APP (APP_ID, APP_NM, APP_DN) VALUES ('appId','appName','appDesc');
-- ApplicationNotification
INSERT INTO APP_NOTIF_MSG (APP_ID,APP_NOTIF_MSG_ID,APP_NOTIF_MSG_NM,APP_NOTIF_MSG_DN) VALUES ('appId','notifyId','notifyName','notifyDesc');
-- ApplicationNotification Message
INSERT INTO LL_NOTIF_MSG_TEMPL (APP_ID,APP_NOTIF_MSG_ID,LCLE_CD,LL_NOTIF_MSG_TEMPL_NM,LL_NOTIF_MSG_TEMPL_DN,LL_NOTIF_MSG_SUBJ_LN_TX,LL_NOTIF_MSG_TEMPL_BODY_TX) VALUES ('appId','notifyId','en_US','templateName','templateDesc','subjectText','Hi ${user}<html>');