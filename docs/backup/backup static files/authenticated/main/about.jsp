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

    <title>About Page</title>
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
                <div id="main-top-container" class="about-containers">
                    <h1>PEA TO-DO APPLICATION</h1>
          
                    <p>Welcome to the PEA TO-DO APPLICATION! My name is Jon Arbell De Ocampo, and I am thrilled to introduce you to my very first web application. PEA stands for Plan, Execute, Achieve, encapsulating the essence of productivity and accomplishment.</p>
                </div>
                
                <div class="about-containers">
                    <h2>The Journey</h2>
            
                    <p>Over the past 2-3 months, I've been actively developing the PEA Todo App while learning HTML, CSS, and JavaScript. This process has not only helped me build practical solutions but also honed my technical skills. The PEA TO-DO APPLICATION is a testament to my commitment to turning ideas into reality through continuous learning and development.</p>
                </div>
            
            
                <div class="about-containers">
                    <h2>Why PEA Todo App?</h2>
            
                    <p>I created the PEA Todo App because I personally struggled with prioritization and task management. This app serves as a tool to help users like me organize tasks effectively, set priorities, and track progress towards achieving their goals.</p>
                </div>
            
                <div class="about-containers">
                    <h2>Features</h2>
                    <ul>
                      <li>
                        <strong>Simple and Intuitive:</strong>Designed to be user-friendly, making task management effortless.
                      </li>
                
                      <li>
                        <strong>Flexible Task Management:</strong> Organize your tasks, set priorities, and track your progress seamlessly.
                      </li>
                
                      <li>
                        <strong>Secure Data Management:</strong>Accessible across devices, ensuring you stay productive wherever you go.
                      </li>
        
                      <li>
                        <strong>Secure Data Management:</strong> Your information and tasks are protected with standard security measures, ensuring a safe and reliable experience.</li>
                    </ul>
                </div>
        
                
            
                <div class="about-containers">
                    <h2>Future Plans</h2>
            
                    <p>I am committed to continuously improving the PEA Todo App. Future updates will focus on enhancing functionality and user experience based on your valuable feedback.</p>
                </div>
            
                
            
                <h3 id="last-parag">Thank you for visiting, and I hope the PEA Todo App helps you plan effectively, execute efficiently, and achieve your goals!</h3>
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

    <nav id="top-nav">
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

                <a href="/${username}">
                    <div id="profile-lists-container">

                        <img class="nav-button" id="profile-pic" src="<%= request.getContextPath() %>/images/try_profile_logo.ico"/>
                        
                        <li class="lists" id="fullname">${fullname}</li>
                    </div>
                </a>
                
                <form action="/pea/logout" method="post">
                    <div id="logout-lists-container">
                        
                        <sec:csrfInput />
                        
                        <img class="nav-button" id="logout-pic" src="<%= request.getContextPath() %>/images/logout.png"/>

                        <input type="submit" id="logout" class="lists" value="Logout"/>
                    </div>

                </form>

                <!-- <footer class="lists">J.ARBELLDEOCAMPO &copy; 2024</footer> -->
            </ul>
        </nav>

        
    </nav>

    <script src="${pageContext.request.contextPath}/js/home.js"></script>
        
    <!-- <script>
        //This code is for showing to the user that the todo is successfully deleted
        const message = "${message}";
        handleMessage(message);
    </script> -->

</body>
</html>