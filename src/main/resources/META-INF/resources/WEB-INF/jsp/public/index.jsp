<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>P E A - log in or sign up</title>

        <link rel="icon" href="<%= request.getContextPath() %>/images/checklist.ico" type="icon">

        <link href="${pageContext.request.contextPath}/css/public/index.css" rel="stylesheet"/>

    </head>
    <body>
        
        <header class="container">
            <h1>PEA TO-DO LIST APPLICATION</h1>
            <hr/>
        </header>

        <main class="container">
            <div id="main-page">
                <div id="advertisement">
                    <h1>Plan,</h1>
                    <h1>Execute,</h1>
                    <h1>Achieve.</h1>
                </div>
        
                <div id="login-page">

                    <form:form modelAttribute="user" action="/pea/login" method="post">

                        <form:input path="username" required="required" class="login-input" type="text" placeholder="Username"/>

                        <div class="login-input" id="password-container">
                            <form:input oninput="onInputPassword(event,'show-password-image','password')" id="password" required="required" path="password" type="password" placeholder="Password"/>
                            <img style="height: 15px; width: 15px;" id="show-password-image" src="<%= request.getContextPath() %>/images/showPassword/close.png" alt="Toggle Password"/>
                        </div>
                        
                        <button id="login-button" class="buttons" type="submit">LOG IN</button>
                    </form:form>

                    <div id="forgot-password-container">
                        <a href="/forgot-password">Forgot password?</a>
                        <hr/>
                    </div>
                    
                    <button class="buttons" id="create-new-account-button">Create new account</button>

                </div>

                

            </div>
            
        </main>

        <div id="create-new-account-container">

            <span id="closeContainer">&times</span>

            <div id="create-new-account-titles">
                <h1>Sign Up</h1>
                <p>It's quick and easy.</p>
            </div>

            
            <hr/>

            <form:form modelAttribute="user" action="/pea/create-account" method="post">

                <div id="create-new-account-names">
                    <form:input maxlength="35" required="required"  path="firstName" type="text" placeholder="First name"/>
                    <form:input maxlength="35" required="required"  path="lastName" type="text" placeholder="Last name"/>
                </div>
                
                <form:input required="required"  path="email" type="email" placeholder="Enter your email address"/>
                <form:input maxlength="25" required="required"  path="username" type="text" placeholder="Create a username"/>
                
                <div id="create-account-password-container">
                    <form:input minlength="8" id="create-new-password" oninput="onInputPassword(event,'show-password-image-create-account','create-new-password')" required="required"  path="password" type="password" placeholder="Create a new password"/>
                    <img style="height: 15px; width: 15px;" id="show-password-image-create-account" src="<%= request.getContextPath() %>/images/showPassword/close.png" alt="Toggle Password"/>
                </div>

                
                <input minlength="8" required="required"  type="password" id="confirmPassword" placeholder="Re-enter your password"/>

                <div id="terms">
                    <p>People who use our service may have uploaded your contact information to PEA</p>
                    <p>By clicking Sign Up, you agree to our <a href="/terms">Terms, Privacy Policy and Cookies Policy</a>.</p>
                </div>

                <button id="sign-up-button" onclick="createAccountPasswordValidation(event)">Sign Up</button>
            </form:form>

            
        </div>

        <footer class="container" >PEA &copy; 2024</footer>
        <script src="${pageContext.request.contextPath}/js/public/index.js"></script>
    </body>
</html>