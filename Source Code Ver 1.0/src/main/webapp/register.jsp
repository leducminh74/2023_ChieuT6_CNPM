<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Perfume Shop - Register</title>
    <!--favicon -->
    <link href="images/faviconn.PNG" rel="icon">
    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="fonts/fontawesome-5.15.4/css/all.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style-form.css">
    <link rel="stylesheet" href="css/edit-style.css">
</head>
<body>
<div class="main">
<!-- Register form -->
    <section class="signup">
        <div class="container">
            <a href="index" class="back-home" title="Back to home"><i class="fas fa-home"></i></a>
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">Sign up</h2>
                    <h4 class="${success.length() > 0 ? "text-success": "text-danger"}">${message}</h4>
                    <form method="post" class="register-form" id="register-form" action="register">
                        <div class="input-line">
                            <div class="form-group">
                                <label > <i class="fas fa-user"></i></label>
                                <input type="text" value="${user}" name="Username" id="username" class="styled-input" placeholder="Your Username"/>
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="input-line">
                            <div class="form-group">
                                <label ><i class="fas fa-envelope"></i></label>
                                <input type="email" value="${email}" name="Email" id="email" class="styled-input" placeholder="Your Email"/>
                            </div>
                            <span class="form-message"></span>
                        </div>
                       <div class="input-line">
                           <div class="form-group">
                               <label ><i class="fas fa-lock"></i></label>
                               <input type="password" name="Password" id="password" class="styled-input" placeholder="Your Password"/>
                           </div>
                           <span class="form-message"></span>
                       </div>
                        <div class="input-line">
                            <div class="form-group">
                                <label ><i class="fas fa-lock"></i></label>
                                <input type="password" name="Re_password" id="re_password" class="styled-input" placeholder="Repeat your password"/>
                            </div>
                            <span class="form-message"></span>
                        </div>

                        <div class="form-group">
                            <input style="width: auto; display: inline-block" type="checkbox" name="agree-term" id="agree-term" class="agree-term" required/>
                            <label for="agree-term" class="label-agree-term">I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure><img src="images/signup-image.jpg" alt="sing up image"></figure>
                    <a href="login" class="signup-image-link">I am already member</a>
                </div>
            </div>
        </div>
    </section>
</div>
<c:if test="${sessionScope.closeTab != null}">
    <script>
        $(document).ready(function() {
            window.location.refresh();
        });
    </script>

</c:if>
<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>

<%--Validate Form--%>
<script src="js/validate.js"></script>
<%--Register--%>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        Validator({
            form: '#register-form',
            formGroupSelector: '.input-line',
            errorSelector: '.form-message',
            rules: [
                Validator.isRequired('#username', 'Please enter your username'),
                Validator.isEmail('#email'),
                Validator.minLength('#password', 3),
                Validator.isRequired('#re_password'),
                Validator.isConfirmed('#re_password', function () {
                    return document.querySelector('#register-form #password').value;
                }, 'Repeat password is incorrect')
            ]
        });

    });
</script>
</body>
</html>
