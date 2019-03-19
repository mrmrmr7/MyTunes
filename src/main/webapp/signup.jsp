<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>


<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Sufee Admin - HTML5 Admin Template</title>
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

<body class="bg-dark">


<div class="sufee-login d-flex align-content-center flex-wrap">
    <div class="container">
        <div class="login-content">
            <div class="login-logo">
                <a href="${pageContext.request.contextPath}/index.jsp">
                    <img class="align-content" src="${pageContext.request.contextPath}/site/images/logo.png" alt="">
                </a>
            </div>


            <div class="login-form">
                <script>
                    function formValidation() {
                        var login = document.forms["signUpForm"]["login"].value;
                        var password = document.forms["signUpForm"]["password"].value;
                        var passwordAgain = document.forms["signUpForm"]["passwordAgain"].value;
                        var email = document.forms["signUpForm"]["email"].value;
                        var firstName = document.forms["signUpForm"]["firstName"].value;
                        var secondName = document.forms["signUpForm"]["secondName"].value;

                        var boolRes = 1;

                        if (!/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email)) {
                            document.forms["signUpForm"]["email"].value = "";
                            document.getElementById("emailId").className = "is-invalid form-control";
                            document.getElementById("emailId").style.color = "red";
                            document.getElementById("emailLabelId").style.color = "red";
                            document.forms["signUpForm"]["email"].value = email;
                            boolRes = 0;
                        } else {
                            document.forms["signUpForm"]["email"].value = "";
                            document.getElementById("emailId").className = "form-control";
                            document.getElementById("emailId").style.color = "";
                            document.getElementById("emailLabelId").style.color = "";
                            document.forms["signUpForm"]["email"].value = email;
                        }

                        if (!/^[0-9a-zA-Z_]{8,32}$/.test(login)) {
                            document.forms["signUpForm"]["login"].value = "";
                            document.getElementById("loginId").className = "is-invalid form-control";
                            document.getElementById("loginId").style.color = "red";
                            document.getElementById("loginLabelId").style.color = "red";
                            document.forms["signUpForm"]["login"].value = login;
                            boolRes = 0;
                        } else {
                            document.forms["signUpForm"]["login"].value = "";
                            document.getElementById("loginId").className = "form-control";
                            document.getElementById("loginId").style.color = "";
                            document.getElementById("loginLabelId").style.color = "";
                            document.forms["signUpForm"]["login"].value = login;
                        }

                        if (password.localeCompare(passwordAgain) !== 0) {
                            document.forms["signUpForm"]["password"].value = "";
                            document.getElementById("passwordId").className = "is-invalid form-control";
                            document.getElementById("passwordId").style.color = "red";
                            document.getElementById("passwordLabelId").style.color = "red";
                            document.forms["signUpForm"]["password"].value = password;

                            document.forms["signUpForm"]["passwordAgain"].value = "";
                            document.getElementById("passwordAgainId").className = "is-invalid form-control";
                            document.getElementById("passwordAgainId").style.color = "red";
                            document.getElementById("passwordAgainLabelId").style.color = "red";
                            boolRes = 0;
                        } else {
                            document.forms["signUpForm"]["password"].value = "";
                            document.getElementById("passwordId").className = "form-control";
                            document.getElementById("passwordId").style.color = "";
                            document.getElementById("passwordLabelId").style.color = "";
                            document.forms["signUpForm"]["password"].value = password;

                            document.forms["signUpForm"]["passwordAgain"].value = "";
                            document.getElementById("passwordAgainId").className = "form-control";
                            document.getElementById("passwordAgainId").style.color = "";
                            document.getElementById("passwordAgainLabelId").style.color = "";
                        }

                        if (!/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,32}$/.test(password)) {
                            document.forms["signUpForm"]["password"].value = "";
                            document.getElementById("passwordId").className = "is-invalid form-control";
                            document.getElementById("passwordId").style.color = "red";
                            document.getElementById("passwordLabelId").style.color = "red";
                            document.forms["signUpForm"]["password"].value = password;
                            boolRes = 0;
                        } else {
                            document.forms["signUpForm"]["password"].value = "";
                            document.getElementById("passwordId").className = "form-control";
                            document.getElementById("passwordId").style.color = "";
                            document.getElementById("passwordLabelId").style.color = "";
                            document.forms["signUpForm"]["password"].value = password;
                        }

                        if (!/^[A-Z][a-z]+$/.test(firstName)) {
                            document.forms["signUpForm"]["firstName"].value = "";
                            document.getElementById("firstNameId").className = "is-invalid form-control";
                            document.getElementById("firstNameId").style.color = "red";
                            document.getElementById("firstNameLabelId").style.color = "red";
                            document.forms["signUpForm"]["firstName"].value = firstName;
                            boolRes = 0;
                        } else {
                            document.forms["signUpForm"]["firstName"].value = "";
                            document.getElementById("firstNameId").className = "form-control";
                            document.getElementById("firstNameId").style.color = "";
                            document.getElementById("firstNameLabelId").style.color = "";
                            document.forms["signUpForm"]["firstName"].value = firstName;
                        }

                        if (!/^[A-Z][a-z]+$/.test(secondName)) {
                            document.forms["signUpForm"]["secondName"].value = "";
                            document.getElementById("secondNameId").className = "is-invalid form-control";
                            document.getElementById("secondNameId").style.color = "red";
                            document.getElementById("secondNameLabelId").style.color = "red";
                            document.forms["signUpForm"]["secondName"].value = secondName;
                            boolRes = 0;
                        } else {
                            document.forms["signUpForm"]["secondName"].value = "";
                            document.getElementById("secondNameId").className = "form-control";
                            document.getElementById("secondNameId").style.color = "";
                            document.getElementById("secondNameLabelId").style.color = "";
                            document.forms["signUpForm"]["secondName"].value = secondName;
                        }

                        return Boolean(boolRes);
                    }
                </script>
                <form action="${pageContext.request.contextPath}/crud" method="post" id="si" name="signUpForm"
                      onsubmit="return formValidation()">
                    <div class="form-group">
                        <label id="loginLabelId">Login</label>
                        <input type="text" class="form-control" placeholder="f.e. mrmrmr7" id="loginId" name="login"
                               value="LoginXyegin">
                    </div>

                    <div class="form-group">
                        <label id="emailLabelId">Email address</label>
                        <input type="email" class="form-control" placeholder="f.e. aliex.s@yandex.by" id="emailId"
                               name="email" value="a@b.cd">
                    </div>

                    <div class="form-group">
                        <label id="firstNameLabelId">First name</label>
                        <input type="text" class="form-control" placeholder="f.e. Alexandr" id="firstNameId"
                               name="firstName" value="Bb">
                    </div>
                    <div class="form-group">
                        <label id="secondNameLabelId">Second name</label>
                        <input type="text" class="form-control" placeholder="f.e. Zaporozhtsev" id="secondNameId"
                               name="secondName" value="Aa">
                    </div>
                    <div class="form-group">
                        <label id="passwordLabelId">Password</label>
                        <input type="text" class="form-control" placeholder="f.e. ********" id="passwordId"
                               name="password" value="EpamClass2019">
                    </div>
                    <div class="form-group">
                        <label id="passwordAgainLabelId">Password again</label>
                        <input type="password" class="form-control" placeholder="f.e. ********" id="passwordAgainId"
                               name="passwordAgain" value="EpamClass2019">
                    </div>
                    <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30">Register</button>

                    <div class="register-link m-t-15 text-center">
                        <p>Already have account? <a href="${pageContext.request.contextPath}/signin.jsp"> Sign in</a>
                        </p>
                    </div>
                    <input type="hidden" name="command" value="signup"/>
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
