<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us</title>

    <link rel="icon" href="<%= request.getContextPath() %>/images/checklist.ico" type="icon">
    <link href="${pageContext.request.contextPath}/css/authenticated/homePage.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/authenticated/about.css" rel="stylesheet"/>
</head>
<body>

    <div id="prompt-message">

    </div>

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
                <div id="main-top-container" class="about-containers">
                    <h1>PEA TO-DO LIST APPLICATION</h1>
          
                    <p>Welcome to the PEA TO-DO LIST APPLICATION! My name is Jon Arbell De Ocampo, and I am thrilled to introduce you to my very first web application. PEA stands for Plan, Execute, Achieve, encapsulating the essence of productivity and accomplishment.</p>
                </div>
                
                <div class="about-containers">
                    <h2>The Journey</h2>
            
                    <p>Over the past three months, I've been immersed in developing the PEA TO-DO LIST APPLICATION. I simultaneously learned HTML, CSS, JavaScript, and Spring Boot, applying these skills as I progressed. Starting with a month of foundational learning, I continued to develop and refine the app, incorporating new knowledge into its design and functionality.</p>

                    <p>The backend of the app is powered by Spring Boot with Java, and it uses PostgreSQL for database management. This integration ensures efficient data handling and robust server-side logic.</p>
                    
                    <p>Completing the app within three months, while still learning and applying new technologies, has been a significant achievement. The PEA TO-DO LIST APPLICATION reflects my dedication to transforming ideas into practical solutions through continuous learning and development.</p>
                </div>
            
            
                <div class="about-containers">
                    <h2>Why PEA TO-DO LIST APPLICATION?</h2>
            
                    <p>I created the PEA TO-DO LIST APPLICATION because I personally struggled with prioritization and task management. This app serves as a tool to help users like me organize tasks effectively, set priorities, and track progress towards achieving their goals.</p>
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
                        
                    <li>
                        <strong>Adding Notifications:</strong> Implementing a notification system to keep users informed and engaged with their tasks.
                    </li>
                    </ul>
                </div>
        
                
            
                <div class="about-containers">
                    <h2>Future Plans</h2>
                    <p>At PEA TO-DO LIST APPLICATION, we're dedicated to continual improvement. Our upcoming updates will focus on:</p>
                    <ul>
                        <li><strong>Enhancing Functionality:</strong> Implementing new features and refining existing ones to better meet user needs.</li>

                        <li><strong>Improving User Experience:</strong> Leveraging user feedback to create a more intuitive and enjoyable application.</li>

                        <li><strong>Strengthening Security:</strong> Upgrading our security measures to protect your data and ensure a reliable experience.</li>

                        <li><strong>Introducing Real-Time Search:</strong> Adding real-time search for to-do items to streamline task management and boost productivity.</li>

                    </ul>
                    
                </div>
            
                
            
                <h3 id="last-parag">Thank you for visiting, and I hope the PEA Todo App helps you plan effectively, execute efficiently, and achieve your goals!</h3>
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

            <div id="add-edit-task-list-container">

                <div id="add-edit-task-container">
                
                    <form:form modelAttribute="todo" method="post">

                        <sec:csrfInput/>

                        <form:input id="todo-id" path="id" type="hidden" required="required"/>
                        
                        <div id="title-description-container">

                            <h3 id="add-edit-title"></h3>

                            <form:input id="add-task-title" path="title" maxlength="35" required="required" autofocus="autofocus" type="text" placeholder="Tile"/>

                            <form:textarea id="shortDescription" path="shortDescription" draggable="false" rows="5" cols="40" placeholder="Short Description" maxlength="200"></form:textarea>

                        </div>

                        <div class="select-list-date" id="select-list-container">
                            <h4>List [Optional]</h4>
                            <select path="listsTodoId">
                                <option value="0">None</option>
                                <option value="1">Personal</option>
                                <option value="2">Work</option>
                            </select>
                        </div>

                        <div class="select-list-date" id="select-date-container">
                            <h4>Target date</h4>
                            <form:input path="date" type="date" required="required"/>
                        </div>


                        <div class="add-edit-task-buttons">
                            <input onmouseup="handleDiscard()" value="Discard Changes" type="reset"/>
                            <input onmouseup="handleSubmit()" value="Save Changes" type="submit"/>
                        </div>
                        
                    </form:form>
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