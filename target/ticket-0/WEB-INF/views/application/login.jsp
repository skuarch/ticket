<%-- 
    Document   : index
    Created on : Jul 16, 2014, 5:29:56 PM
    Author     : skuarch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />         
        <title><spring:message code="text1" /></title>
    </head>
    <body>        
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><spring:message code="text2"/></h3>
                        </div>
                        <div class="panel-body">
                            <form action="home.html" role="form">
                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="e-mail" name="email" type="email" autofocus>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="password" name="password" type="password" value="">
                                    </div>
                                    <br/>
                                    <!-- Change this to a button or input when using this as a form -->
                                    <a href="home.html" class="btn btn-lg btn-success btn-block">Login</a>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>eval("${js}");</script>
        <script src="<c:url value="/resources/js/app.js"/>"></script>
    </body>
</html>
