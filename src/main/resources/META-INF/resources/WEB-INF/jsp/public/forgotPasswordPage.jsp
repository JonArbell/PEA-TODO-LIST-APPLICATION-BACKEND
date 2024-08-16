<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Forgot Password?</title>

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

                    <form action="/forgot-password" method="post">

                        <input name="username" required="required" class="login-input" type="text" placeholder="Username"/>

                        <div class="login-input" id="password-container">
                            <input oninput="onInputPassword(event,'show-password-image','password')" id="password" required="required" name="password" type="password" placeholder="Password"/>
                            <img style="height: 15px; width: 15px;" id="show-password-image" src="<%= request.getContextPath() %>/images/showPassword/close.png" alt="Toggle Password"/>
                        </div>
                        
                        <button id="login-button" class="buttons" type="submit">LOG IN</button>
                    </form>

                    <div id="forgot-password-container">
                        <a href="/pea/forgot-password">Forgot password?</a>
                        <hr/>
                    </div>
                    
                    <button class="buttons" id="create-new-account-button">Create new account</button>

                </div>

                

            </div>
            
        </main>

        <footer class="container" >PEA &copy; 2024</footer>
        <script src="${pageContext.request.contextPath}/js/public/index.js"></script>
    </body>
</html>