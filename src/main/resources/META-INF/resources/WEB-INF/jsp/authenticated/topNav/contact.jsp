<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us</title>

    <link rel="icon" href="<%= request.getContextPath() %>/images/checklist.ico" type="icon">
    <link href="${pageContext.request.contextPath}/css/authenticated/homePage.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/authenticated/contact.css" rel="stylesheet"/>
</head>
<body>

    <header>
        <div>

            <ul>
                <li class="list-no-hover">
                    <h1 onclick="goHome()">P E A</h1>
                </li>

                <li onclick="goHome()" class="list-no-hover">
                    <img src="<%= request.getContextPath() %>/images/checklist.png"/>
                </li>

                <li id="list-search">
                    <form method="get" action="/search">

                        <input type="search" name="query" id="" placeholder="Search..."/>
        
                    </form>
                </li>

                <a href="/pea/home">
                    <li>
                        <h2>Home</h2>
                    </li>
                </a>

                <a href="/about-us">
                    <li>
                        <h2>About</h2>
                    </li>
                </a>


                <a href="/contact-us">
                    <li>
                        <h2>Contact</h2>
                    </li>
                </a>

                <li class="list-no-hover" id="list-profile-logo">
                    <img class="nav-button" id="profile-logo" src="<%= request.getContextPath() %>/images/try_profile_logo.ico"/>


                    <div id="profile-modal">
                        <a href="/pea/${username}">${username}</a>

                        <!-- <form method="post" action="/pea/logout">

                            <button type="submit">Log Out</button>

                        </form> -->
                        <a href="/pea/logout">Log Out</a>

                    </div>

                </li>

            </ul>

        </div>
    </header>

    <main>
        
        <div id="left-container" class="containers">
            <h2>Menu</h2>
            <hr/>
            <div>
                <h3>Tasks</h3>
                <ul>
                    <a href="/pea/todays-tasks">
                        <li>Today's Tasks</li>
                    </a>

                    <a href="/pea/completed-tasks">
                        <li>Completed Tasks</li>
                    </a>

                    <a href="/pea/all-tasks">
                        <li>All Tasks</li>
                    </a>

                </ul>
            </div>
            
            <div id="lists-container">
                <h3>Lists</h3>
                <ul>
                    <c:forEach items="${lists}" var="list">
                        <a href="/pea/list/${list.id}">
                            <li>${list.listName}</li>
                        </a>
                    </c:forEach>
                </ul>
            </div>
            <hr/>
            <footer>PEA &copy; 2024</footer>
        </div>

        <div id="middle-container" class="containers">

            <div>
                <div id="main-top-container" class="contact-containers">
                    <h1>GET IN TOUCH</h1>
                    <p>Thank you for using the PEA TO-DO LIST APPLICATION! We value your feedback and are here to assist with any questions or concerns you may have.</p>
                </div>
                
                <div class="contact-containers">
                    <h2>Contact Information</h2>
                
                    <ul>
                        <li>
                            <strong>Email:</strong> deocampo.arbelldonor@gmail.com
                        </li>
        
                        <li>
                            <strong>Phone number:</strong> 0977-446-9490
                        </li>
        
                        <li>
                            <strong>Address:</strong> Taguig, City
                        </li>
                    </ul>
                </div>
                    
          
                <div class="contact-containers">
                    <h2>Social Media</h2>
                    <ul>
        
        
                      <li>
                        <a href="https://www.linkedin.com/in/jon-arbell-donor-de-ocampo-134083284/">LinkedIn</a>
                      </li>
        
        
                      <li>
                        <a href="https://github.com/JonArbell" >GitHub</a>
                      </li>
                    
                      
                      <li>
                        <a href="https://web.facebook.com/j.arbelldeocampo">Facebook</a>
                      </li>
                
                      <li>
                        <a href="https://www.instagram.com/j.arbelldeocampo/">Instagram</a>
                      </li>
        
                      
                    </ul>
                </div>
        
        
                <div class="contact-containers">
                    <h2>Feedback and Support</h2>
                
                    <p>We appreciate your feedback and are here to help you with any support you need. Your input is invaluable for enhancing the PEA TO-DO LIST APPLICATION.</p>
                </div>

                <div class="contact-containers">
                    <h2>Feedback Form [Optional]</h2>
                   
                    <form>
                        <label for="full-name">Full name: </label>
                        <input id="full-name" type="text" placeholder="Enter your full name">
                        <br/>
        
                        <label for="email">Email: </label>
                        <input id="email" type="email" placeholder="Enter your email">
                        <br/>
        
                        <div>
                            <h4>Feedback Type</h4>
                            
                            <label for="user-interface">User Interface</label>
                            <input id="user-interface" type="checkbox"/>
        
                            <label for="functionality">Functionality</label>
                            <input id="functionality" type="checkbox"/>
        
                            <label for="performance">Performance</label>
                            <input id="performance" type="checkbox"/>
                            
                            <label for="ux">User Experience</label>
                            <input id="ux" type="checkbox"/>
        
                            <br/>
        
                            <label for="other">Other:</label>
                            
                            <input id="other" type="text" placeholder="Please specify">
        
        
                        </div>
                        
                        <br/>
                        <label for="detailed">Provide detailed feedback about the user interface:</label>
                        <br/>
                        <textarea id="detailed" maxlength="300" rows="5" cols="70" placeholder="What did you like or dislike?"></textarea>
                        
                        
                    </form>
                </div>
                
                <div class="contact-containers">
                    <h2>Thank You</h2>
                
                    <p>Thank you for using the PEA TO-DO LIST APPLICATION! We are committed to providing you with a productive and enjoyable task management experience.</p>
                </div>
            </div>
            
            
        </div>

        <div id="right-container" class="containers">

            <div>
                <h2>Welcome,</h2>
                <h2>${fullname}</h2>
            </div>
            
            <hr/>

            <div id="date-and-time-progress-display-container">
                <div class="date-and-time">
                    <p id="date"></p>
                    <p id="time"></p>
                </div>
    
                <div class="task-progress">
    
                    <section>
                        <div>
                            <h3>Completed Task:</h3>
    
                            <div></div>
                        </div>
                        
                        <div id="incomplete-label-container">
                            <h3>Incomplete Task:</h3>
    
                            <div id="incomplete-donut"></div>
                        </div>
    
                    </section>
    
                    <div id="donut" data-average="${average}">
                        <div>
                            <p>${average}</p>
                        </div>
                    </div>
    
                </div>
            </div>

            <hr/>

            <footer>Designed and developed by Jon Arbell De Ocampo</footer>
        
        </div>

    </main>

    <script src="${pageContext.request.contextPath}/js/authenticated/profileModal/script.js"></script>

    <script src="${pageContext.request.contextPath}/js/authenticated/goToHome/script.js"></script>

    <script src="${pageContext.request.contextPath}/js/authenticated/dynamicallyProgress/script.js"></script>

    <script src="${pageContext.request.contextPath}/js/authenticated/dateTime/script.js"></script>

</body>
</html>