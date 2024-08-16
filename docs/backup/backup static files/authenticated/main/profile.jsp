<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="icon" href="<%= request.getContextPath() %>/images/checklist.ico" type="icon">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">

    <title>Home Page</title>
</head>
<body>
    <div class="nav">
        <!-- <div id="top-container">
            <img id="logo" src="<%= request.getContextPath() %>/images/checklist.png">
            <h1 id="title">P E A</h1>
        </div> -->

        <nav id="nav">

            <h2 id="welcome" class="home" onclick="home()">Welcome, ${username}!</h2>

            <div class="logo-and-search">
                <img class="home" onclick="home()" id="logo" src="<%= request.getContextPath() %>/images/checklist.ico"/>

                <form action="/search" method="get" id="search-form">
                    <input id="search-input" type="search" placeholder="Search...">
                </form>
            </div>

            

            <a href="/home" class="nav-button">Home</a>
            <a href="/about-us" class="nav-button">About Us</a>
            <a href="/contact-us" class="nav-button">Contact Us</a>

            <img class="nav-button" id="profile_logo" src="<%= request.getContextPath() %>/images/try_profile_logo.ico" onclick="profile()"/>


            <div id="modal-profile">
                <ul id="ul-profile">

                    <a href="/${username}">
                        <div id="profile-lists-container">

                            <img class="nav-button" id="profile-pic" src="<%= request.getContextPath() %>/images/try_profile_logo.ico"/>
                            
                            <li class="lists" id="fullname">${fullname}</li>
                        </div>
                    </a>
                    
                    <a href="/logout">
                        <div id="logout-lists-container">

                            <img class="nav-button" id="logout-pic" src="<%= request.getContextPath() %>/images/logout.png"/>
    
                            <li id="logout" class="lists">Logout</li>
                        </div>
                    </a>

                    <p class="lists">J.ARBELLDEOCAMPO &copy; 2024</p>
                </ul>
            </div>

            
       </nav>
    </div>
    
    </div>

    
    <div>

    </div>
    
    
    <footer>
        J.ARBELLDEOCAMPO &copy; 2024
    </footer>

    
</body>
</html>