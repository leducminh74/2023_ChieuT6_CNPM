<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Perfume Shop - Change Password</title>
    <!--favicon -->
    <link href="./images/faviconn.PNG" rel="icon">
    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="fonts/fontawesome-5.15.4/css/all.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style-form.css">
    <link rel="stylesheet" href="css/edit-style.css">
</head>
<body>
<div class="main">
    <!-- Sing in  Form -->
    <section class="sign-in">
        <div class="container">
            <a href="index" class="back-home" title="Back to home"><i class="fas fa-home"></i></a>
            <div class="signin-content">
                <div class="signin-image">
                    <figure><img src="images/signin-image.jpg" alt="Forgot pass image"></figure>
                </div>

                <div class="signin-form">
                    <h2 class="form-title">Change Password</h2>
                    <h4 class="text-danger">${message}</h4>
                    <form method="post" class="login-form" id="change-password-form" action="changepassword">
                        <div class="input-line">
                            <div class="form-group">
                                <label ><i class="fas fa-lock material-icons-name"></i></label>
                                <input type="password" name="Current_password" id="current-password" class="styled-input" placeholder="Your Current Password"/>
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="input-line">
                            <div class="form-group">
                                <label ><i class="fas fa-lock"></i></label>
                                <input type="password" name="New_password" id="new-password" class="styled-input" placeholder="Your New Password"/>
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="input-line">
                            <div class="form-group">
                                <label ><i class="fas fa-lock"></i></label>
                                <input type="password" name="Renew_password" id="re-newpassword" class="styled-input" placeholder="Repeat New Password"/>
                            </div>
                            <span class="form-message"></span>
                        </div>

                        <div class="form-group form-button">
                            <input type="submit" name="forgotpass" id="forgotpass" class="form-submit" value="Change"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>

<!--validate-->
<script src="js/validate.js"></script>
<!--login-->
<script>
    document.addEventListener('DOMContentLoaded', function () {
        Validator({
            form: '#change-password-form',
            formGroupSelector: '.input-line',
            errorSelector: '.form-message',
            rules: [
                Validator.isRequired('#current-password', 'Please enter your current password'),
                Validator.minLength('#new-password',3),
                Validator.isRequired('#re-newpassword'),
                Validator.isConfirmed('#re-newpassword', function () {
                    return document.querySelector('#change-password-form #new-password').value;
                }, 'Repeat password is incorrect')
            ]
        });

    });
</script>

</body>
</html>