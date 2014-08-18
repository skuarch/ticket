<%-- 
    Document   : testNotificationForm
    Created on : Aug 4, 2014, 5:04:42 PM
    Author     : skuarch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<c:url value="/resources/js/app.js"/>"></script>
        <title>Test Notification</title>
    </head>
    <body>
        <h1>Test Notification</h1>
        <input id="text" type="text" value="hello !!!" />
        <input type="button" value="send notification" onclick="testNotification(document.getElementById('text').value);" />
    </body>
</html>
