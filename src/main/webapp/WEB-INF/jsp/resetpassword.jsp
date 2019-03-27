<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%--<%@ taglib uri="/WEB-INF/messagetag.tld" prefix="my" %>--%>
<fmt:requestEncoding value="UTF-8"/>


<c:choose>
    <c:when test="${not empty requestScope.get('lang')}">
        <fmt:setLocale value="${requestScope.get('lang')}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${cookie['locale'].value}"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="language" var="bundle" scope="application"/>



<html>
<!--<![endif]-->

<head>

    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title><fmt:message key="title.resetpassword" bundle="${bundle}" /></title>
    <meta charset="UTF-8"/>
    <meta charset="UTF-8"/>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/site/images/logo_icon.png">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/themify-icons/css/themify-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/selectFX/css/cs-skin-elastic.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>



</head>

<body class="bg-dark" style="display: block">

<div class="sufee-login d-flex align-content-center flex-wrap">

    <%--<my:msg label="signin.failsignin" message="signin.failsignin" showFlag="${true}" bundle="${bundle}"/>--%>

    <div class="container">
        <div class="login-content">
            <div class="login-logo" style="width: 100%">
                <a href="${pageContext.request.contextPath}/index.jsp">
                    <img class="align-content" src="${pageContext.request.contextPath}/site/images/logo_name.png" alt="">
                </a>
            </div>
            <div class="login-form">
                <script>
                    function formValidation() {
                        var login = document.forms["signInForm"]["login"].value;
                        var password = document.forms["signInForm"]["password"].value;

                        if ((!/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,32}$/.test(password))
                            || (!/^[0-9a-zA-Z_]{8,32}$/.test(login))) {
                            document.forms["signInForm"]["login"].value = "";
                            document.forms["signInForm"]["password"].value ="";
                            document.getElementById("loginId").className = "is-invalid form-control";
                            document.getElementById("passwordId").className = "is-invalid form-control";
                            document.getElementById("loginId").style.color = "red";
                            document.getElementById("passwordId").style.color = "red";
                            document.getElementById("loginLabelId").style.color = "red";
                            document.getElementById("passwordLabelId").style.color = "red";
                            document.forms["signInForm"]["login"].value = login;
                            document.forms["signInForm"]["password"].value = password;
                            return false;
                        }

                        else {
                            return true;
                        }
                    }
                </script>

                <%--<c:if test="${failSignIn}">--%>
                    <%--<div class="sufee-alert alert with-close alert-dark alert-dismissible fade show">--%>
                        <%--<span class="badge badge-pill badge-dark">XXX</span>--%>
                        <%--<fmt:message key="signin.failsignin" bundle="${bundle}"/>--%>
                        <%--<button type="button" class="close" data-dismiss="alert" aria-label="Close">--%>
                            <%--<span aria-hidden="true">X</span>--%>
                        <%--</button>--%>
                    <%--</div>--%>
                <%--</c:if>--%>

                <form action="${pageContext.request.contextPath}/crud"  method="post" id="si" name="signInForm" onsubmit="return formValidation()">
                    <div class="form-group">
                        <label id="passwordLabelId"><fmt:message key="restartpassword.password" bundle="${bundle}" /></label>
                        <input type="password" class="form-control" placeholder="<fmt:message key="restartpassword.password" bundle="${bundle}" />" id="passwordId" name="password" required>
                    </div>
                    <div class="form-group">
                        <label id="passwordAgainLabelId"><fmt:message key="restartpassword.passwordagain" bundle="${bundle}" /></label>
                        <input type="password" class="form-control" placeholder="<fmt:message key="restartpassword.passwordagain" bundle="${bundle}" />" id="passwordAgainId" name="password" required>
                    </div>

                    <button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30" form="si"><fmt:message key="restartpassword.change" bundle="${bundle}"/></button>

                    <div class="register-link m-t-15 text-center">
                        <p> <fmt:message key="restartpassword.advicesignin" bundle="${bundle}"/><a href="crud?command=viewSignInPage">
                            <fmt:message key="restartpassword.showsignin" bundle="${bundle}"/>
                        </a></p>
                    </div>
                    <input type="hidden" name="userId" value="${userId}">
                    <input type="hidden" name="command" value="finishRestartPassword">

                </form>
            </div>
        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/site/vendors/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/popper.js/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/site/assets/js/main.js"></script>


</body>

</html>
