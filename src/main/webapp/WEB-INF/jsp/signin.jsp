<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <title>Sufee Admin - HTML5 Admin Template</title>
    <meta charset="UTF-8"/>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta charset="UTF-8"/>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/site/apple-icon.png">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/site/favicon.ico">


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

        <div class="container">
            <div class="login-content">
                <div class="login-logo" style="width: 100%">
                    <a href="${pageContext.request.contextPath}/index.jsp">
                        <img class="align-content" src="${pageContext.request.contextPath}/site/images/logo.png" alt="">
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

                    <c:if test="${succesRegStart}">
                        <div class="sufee-alert alert with-close alert-dark alert-dismissible fade show">
                            <span class="badge badge-pill badge-dark">Success</span>
                            <fmt:message key="signin.signinmessage" bundle="${bundle}"/>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">X</span>
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${succesRegFinish}">

                        <div class="sufee-alert alert with-close alert-dark alert-dismissible fade show">
                            <span class="badge badge-pill badge-dark">Success</span>
                            <fmt:message key="signin.signinfinishmessage" bundle="${bundle}"/>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">X</span>
                            </button>
                        </div>
                    </c:if>
                    <form action="${pageContext.request.contextPath}/crud"  method="post" id="si" name="signInForm" onsubmit="return formValidation()">
                        <div class="form-group">
                            <label id="loginLabelId"><fmt:message key="signin.login" bundle="${bundle}" /></label>
                            <input type="text" class="form-control" placeholder="Email" id="loginId" name="login" autofocus>
                        </div>
                        <div class="form-group">
                            <label id="passwordLabelId"><fmt:message key="signin.password" bundle="${bundle}" /></label>
                            <input type="password" class="form-control" placeholder="<fmt:message key="signin.password" bundle="${bundle}" />" id="passwordId" name="password" required>
                        </div>
                        <div class="checkbox">
                            <label class="pull-right">
                                <a href="${pageContext.request.contextPath}/site#">
                                    <fmt:message key="signin.forgetpassword" bundle="${bundle}"/>
                                </a>
                            </label>
                        </div>
                        <button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30" form="si"><fmt:message key="signin.button.language.sign" bundle="${bundle}"/></button>

                        <div class="register-link m-t-15 text-center">
                            <p> <fmt:message key="signin.advicetosignin" bundle="${bundle}"/><a href="${pageContext.request.contextPath}/crud?command=viewSignUpPage">
                                <fmt:message key="signin.linktosign" bundle="${bundle}"/>
                            </a></p>
                        </div>
                        <input type="hidden" name="command" value="signin">

                    </form>

                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/crud" method="post" id="changeLangForm" name="ChangeLang">
                            <button type="submit" class="btn btn-secondary" form="changeLangForm" name="lang" value="ru">
                                <fmt:message key="signin.button.language.russian" bundle="${bundle}"/>
                            </button>
                            <button type="submit" class="btn btn-secondary" form="changeLangForm" name="lang" value="en">
                                <fmt:message key="signin.button.language.english" bundle="${bundle}"/>
                            </button>

                            <input type="hidden" name="command" value="changeLangInSignIn" />
                            <input type="hidden" name="from" value="${pageContext.request.contextPath}/crud?command=viewSignInPage" />
                        </form>
                    </div>
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
