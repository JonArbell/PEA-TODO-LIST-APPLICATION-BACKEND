<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta name="csrf-token" content="${_csrf.token}"/>
    
    <link rel="icon" href="<%= request.getContextPath() %>/images/checklist.ico" type="icon">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">

    <title>Contact</title>
</head>
<body>

    <nav id="top-nav-container">
        <div id="top-nav">

            <ul>
                <div onclick="pending()" class="pending">
                
                    <h1 id="title">P E A</h1>
                
                </div>
    
                <li>
                    <div class="logo-and-search">
                
                        <img onclick="pending()" class="pending" id="logo" src="<%= request.getContextPath() %>/images/checklist.ico"/>
                        
                        <form action="/search" method="get" id="search-form">
                            <input id="search-input" type="search" placeholder="Search...">
                        </form>
                    </div>
                </li>
    
                <li>
                    <a href="/pea/pending" class="nav-button">Pending</a>
                </li>
    
                <li>
                    <a href="/pea/about" class="nav-button">About</a>
                </li>
    
                <li>
                    <a href="/pea/contact" class="nav-button">Contact</a>
                </li>
    
                <li>
                    <img class="nav-button" id="profile_logo" src="<%= request.getContextPath() %>/images/try_profile_logo.ico" onclick="profile()"/>
                </li>
            </ul>
    
    
            <nav id="modal-profile">
                <ul id="ul-profile">
    
                    <a href="/pea/${username}">
                        <div id="profile-lists-container">
    
                            <img class="nav-button" id="profile-pic" src="<%= request.getContextPath() %>/images/try_profile_logo.ico"/>
                            
                            <li class="lists" id="username">${username}</li>
                        </div>
                    </a>
    
                    <form action="/pea/logout" method="post">
                        <div id="logout-lists-container">
                            
                            <sec:csrfInput />
                            
                            <img class="nav-button" id="logout-pic" src="<%= request.getContextPath() %>/images/logout.png"/>
    
                            <input type="submit" id="logout" class="lists" value="Logout"/>
                        </div>
    
                    </form>
    
                    <p class="lists">J.ARBELLDEOCAMPO &copy; 2024</p>
                </ul>
            </nav>
    
            
        </div>
    </nav>



    <main id="main">

        <div id="left-nav-div" >
            <nav id="left-nav-container">

                <h2>Menu</h2>
    
                <hr/>

                <div id="tasks-lists-container">
                    <h4>Tasks</h4>
                    <ul>
                        <a href="/pea/tasks/today">
                            <li>Today's Tasks</li>
                        </a>
                        
                        <a href="/pea/tasks/upcoming">
                            <li>Upcoming Tasks</li>
                        </a>
                        
                        <a href="/pea/tasks/completed">
                            <li>Completed Tasks</li>
                        </a>

                        <a href="/pea/tasks/all">
                            <li>All Tasks</li>
                        </a>
                        
                    </ul>
                </div>
    
    
                <div id="lists-of-tasks-container-form">
    
                    <form action="/" method="post">
                        
                    </form>
                    
                </div>
    
    
                <div id="lists-of-tasks-container">
                    <h4 style="margin-bottom: 2.5%;">Lists</h4>
                    <ul>
                        <section id="lists-of-tasks">
                            
                                <li>Personal</li>
                                <li>Work</li>
                                
                        </section>
                        
                    </ul>
                </div>
    
                <hr/>
                <footer>
                    PEA &copy; 2024
                </footer>
            </nav>
        </div>

        <div id="main-scroll">
            
            <div class="main">

                <div id="main-top-container" class="contact-containers">
                    <h1>GET IN TOUCH</h1>
                    <p>Thank you for using the PEA TO-DO APPLICATION! We value your feedback and are here to assist with any questions or concerns you may have.</p>
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
                
                    <p>We appreciate your feedback and are here to help you with any support you need. Your input is invaluable for enhancing the PEA TO-DO APPLICATION.</p>
                </div>

                <div class="contact-containers">
                    <h2>Feedback Form (Optional)</h2>
                    <!-- <p>[Optional]</p> -->
                    <form>
                        <label for="text">Full name: </label>
                        <input type="text" placeholder="Enter your full name">
                        <br/>
        
                        <label style="margin-right: 39px;" for="text">Email: </label>
                        <input type="email" placeholder="Enter your email">
                        <br/>
        
                        <div>
                            <h4>Feedback Type</h4>
                            
                            <label for="checkbox">User Interface</label>
                            <input type="checkbox">
        
                            <label for="checkbox">Functionality</label>
                            <input type="checkbox">
        
                            <label for="checkbox">Performance</label>
                            <input type="checkbox">
                            
                            <label for="checkbox">User Experience</label>
                            <input type="checkbox">
        
                            <br/>
        
                            <label for="text">Other:</label>
                            
                            <input style="width: 45%; text-align: center;" type="text" placeholder="Please specify">
        
        
                        </div>
                        
                        <br/>
                        <label>Provide detailed feedback about the user interface:</label>
                        <br/>
                        <textarea style="text-align: center;" rows="5" cols="50" placeholder="What did you like or dislike?"></textarea>
                        
                        
                    </form>
                </div>
                
                <div class="contact-containers">
                    <h2>Thank You</h2>
                
                    <p>Thank you for using the PEA TO-DO APPLICATION! We are committed to providing you with a productive and enjoyable task management experience.</p>
                </div>


            </div>

        </div>

        
        <div id="right-nav-div">
            <nav id="right-nav-container">
                <div id="welcome">
                    <h2>Welcome,</h2>
                    <h2>${fullname}</h2>
                </div>

                
                <hr/>

                <div id="no-custom">
                    <div id="time-and-date">
                        <div id="date-calendar" class="time-and-date">
    
                        </div>
        
                        <div id="clock" class="time-and-date">
    
                        </div>
                    </div>
    
    
                    <div id="donut-container">
    
                        <div class="donut-label">
                            <h5>Completed Tasks:</h5>
                            <div class="mini-donut"></div>
                        </div>
    
                        <div class="donut-label">
                            <h5>Incomplete Tasks:</h5>
                            <div id="incomplete-task-donut" class="mini-donut"></div>
                        </div>
                        
                        
    
                        <div class="donut">
                            <div class="donut-inner"></div>
                            <div class="percentage">70%</div>
                            <!-- <div class="percentage">${percentage}</div> -->
                        </div>
                    </div>
                </div>

                
                <hr class="last-hr"/>
                <footer>
                    Designed and developed by Jon Arbell De Ocampo
                </footer>
            </nav>
        </div>

    </main>

    <footer>
        J.ARBELLDEOCAMPO &copy; 2024
    </footer>

    <script src="${pageContext.request.contextPath}/js/home.js"></script>
        
    <script>
        //This code is for showing to the user that the todo is successfully deleted
        const message = "${message}";
        handleMessage(message);
    </script>

</body>
</html>