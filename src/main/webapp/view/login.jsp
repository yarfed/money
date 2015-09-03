
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Please Login" scope="request"/>

<c:url value="/login" var="loginUrl"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <script src="${ctx}/resources/registration.js"></script>
  <script>
    $(document).ready(function () {
        init('${ctx}');
    });

  </script>
</head>
<form action="${loginUrl}" method="post">
    <c:if test="${param.error != null}">
        <div class="alert alert-error">
            Failed to login.
            <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
            </c:if>
        </div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div class="alert alert-success">
            You have been logged out.
        </div>
    </c:if>
    <label for="username">Username</label>
    <input type="text" id="username" name="username"/><br>
    <label for="password">Password</label>
    <input type="password" id="password" name="password"/>
    <div class="form-actions">
        <input id="submit" class="btn" name="submit" type="submit"
               value="Login"/>
    </div>
</form>
<button id="demo">try me</button> <- push to demo enter<br><br>
if you do not have a login please register.
<span id="message"></span>
<div id="registrationForm">
    <label for="usernameRegistration">Username</label>
    <input type="text" id="usernameRegistration"/><br>
    <label for="passwordRegistration">Password</label>
    <input type="password" id="passwordRegistration" /><br>
    <label for="passwordConfirm">Confirm</label>
    <input type="password" id="passwordConfirm" /><br>
    <input id="submitRegistration"  type="button"
           value="Registration"/>
</div>
</html>