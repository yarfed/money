<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.07.2015
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.min.js"></script>
    <script src="resources/lib/angular-route.min.js"></script>

    <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/manual.css">


    <script src="resources/app.js"></script>
    <script src="resources/account/account.js"></script>
    <script src="resources/category/category.js"></script>
    <script src="resources/operation/operation.js"></script>
    <script src="resources/currency/currency.js"></script>

</head>
<body ng-app="moneyApp">
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <span id="message">login:user pass:user</span>

            <div id="nav-account">
                Welcome:&nbsp;
                <sec:authentication property="name"/><br>
                <c:url var="logoutUrl" value="/logout"/>
                <a id="navLogoutLink" href="${logoutUrl}">Logout</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-4 col-md-3" style="text-align: left">
            <div class="panel panel-default">
                <ul class="nav nav-pills nav-stacked">
                    <li><a href="#/operations"><span class="glyphicon glyphicon-pencil"></span> Operation</a></li>
                    <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                            <span class="glyphicon glyphicon-edit"></span>
                            Change Data<b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#/changeCategory/income"><span class="glyphicon glyphicon-import"></span> incoming categories</a>
                            </li>
                            <li><a href="#/changeCategory/expense"><span class="glyphicon glyphicon-export"></span> expense categories</a></li>
                            <li><a href="#/changeAccounts"><span class="glyphicon glyphicon-briefcase"></span> accounts</a></li>
                            <li><a href="#/changeCurrency"><span class="glyphicon glyphicon-usd"></span> currency</a></li>
                        </ul>
                    </li>
                    <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Reports</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-calendar"></span> Planning</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-piggy-bank"></span> Accumulation</a></li>
                </ul>
            </div>
        </div>
        <div class="col-xs-8 col-md-9">
            <ng-view></ng-view>
        </div>
    </div>
</div>
</body>
</html>
