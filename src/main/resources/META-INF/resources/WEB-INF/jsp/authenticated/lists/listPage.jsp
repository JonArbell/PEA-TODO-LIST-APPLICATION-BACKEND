<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>P E A | List - ${getListName}</title>

    <link rel="icon" href="<%= request.getContextPath() %>/images/checklist.ico" type="icon">

    <link href="${pageContext.request.contextPath}/css/authenticated/homePage.css" rel="stylesheet"/>

</head>
<body>

    <div id="prompt-message">

    </div>

    <header>
        <div class="dim">

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
                        <a id="profile-modal-username" href="/pea/${username}">${username}</a>

                        <!-- <form method="post" action="/pea/logout">

                            <button type="submit">Log Out</button>

                        </form> -->
                        <a href="/pea/logout">Log Out</a>

                        <footer>PEA &copy; 2024 Made with &hearts; by Jon Arbell De Ocampo</footer>
                    </div>

                </li>

            </ul>

        </div>
    </header>

    <main>
        
        <div id="left-container" class="containers dim">
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

        <div id="middle-container" class="containers dim">

            <!-- <hr id="panis"/> -->
            <div id="tasks-display-container">

                <button id="create-new-button">Create New</button>

                <div id="create-new-modal-container">
                    <h2>Create New Item</h2>

                    <div id="create-new-item-buttons-container">
                        <button id="create-new-todo" class="create-new-item-buttons">To-do</button>
                        <button id="create-new-list" class="create-new-item-buttons">List</button>
                    </div>
                    
                </div>


                <!-- <div>
                    <h1>${getList}</h1>
                </div> -->


                <c:choose>
                    <c:when test="${empty tasksOfList}">
                        <h1 class="empty">No pending tasks in your ${getListName} list! Click on 'Create New' to get started!</h1>


                    </c:when>

                    <c:otherwise>

                        <div id="title">
                            <div id="total-pending-tasks">
                                <h2>${getListName}:</h2>

                                <div>
                                    <h2>${totalTaskOfList}</h2>
                                </div>
                            </div>

                            
                            <form id="sort-container">
                                <label>Sort By:</label>
                                <select onchange="sortBy()" id="sort">
                                    <option value="1">Date Modified</option>
                                    <option value="2">Title</option>
                                    <option value="3">Target Date</option>
                                </select>
                            </form>


                        </div>

                        <div id="middle-hr-container">
                            <hr/>
                        </div>

                        <!-- <div id="edit-delete-list-container">

                            <c:if test="${getListName != 'Personal' && getListName != 'Work'}">
                                <form method="post">
                                    <button type="submit">Edit list name</button>
    
                                    <button id="delete-list-button" type="submit">Delete this list</button>
                                </form>
                            </c:if>

                        </div> -->
                        
                        <div id="todo-lists-container">
                            <h3 id="to-do-list">TO-DO LIST:</h3>
                            <c:forEach items="${tasksOfList}" var="todo">

                                <div data-done="${todo.done}" id="${todo.id}" class="task-container">

                                    <div>
                                        <a href="">
                                            <h2>View Details</h2>
                                        </a>
                                        
                                    </div>
                                    
                                    <div id="task-container-header">
                                        <h3>Title</h3>
                                        <h3>Completed</h3>
                                        <h3>Target Date</h3>
                                        
                                    </div>
                                    <hr/>
                                    <div id="task-container-data">
                                        <p>${todo.title}</p>
                                        <c:choose>
                                            <c:when test="${todo.done}">
                                                <p>Yes</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p>No</p>
                                            </c:otherwise>
                                        </c:choose>
                                        <p>${todo.formattedDate}</p>
                                    </div>
                                    <hr/>

                                    <div id="task-buttons">
                                        <form id="form-${todo.id}" action="">
                                            <button onmouseup="deleteTodoItem(`${todo.id}`)" id="task-delete-button">Delete</button>

                                            <c:if test="${!todo.done}">
                                                <button onmouseup="markAsCompleteItem(`${todo.id}`)" id="task-mark-as-complete-button">Mark as complete</button>

                                                <button type="button" onmouseup="handleEditTodoItem(`${todo.id}`,`${todo.title}`,`${todo.shortDescription}`,`${todo.date}`,`${todo.lists.id}`)" id="task-edit-button">Edit Task</button>
                                            </c:if>

                                        </form>
                                        
                                    </div>

                                </div>

                                

                            </c:forEach>
                        </div>
                        
                    </c:otherwise>

                </c:choose>


            </div>
            
        </div>

        <div id="right-container" class="containers dim">

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

            <div id="add-edit-task-container">

                <form:form modelAttribute="todo" method="post">

                    <sec:csrfInput/>

                    <form:input id="todo-id" path="id" hidden="hidden"/>
                    
                    <div id="title-description-container">

                        <h3 class="add-edit-title"></h3>

                        <form:input id="add-task-title" path="title" maxlength="35" required="required" autofocus="autofocus" type="text" placeholder="Tile"/>

                        <form:textarea id="shortDescription" path="shortDescription" draggable="false" rows="5" cols="40" placeholder="Short Description" maxlength="200"></form:textarea>

                    </div>

                    <div class="select-list-date" id="select-list-container">
                        <h4>List [Optional]</h4>

                        <form:select path="lists">
                            <form:option value="0">None</form:option>

                            <c:forEach items="${lists}" var="list">
                                <form:option value="${list.id}">${list.listName}</form:option>
                            </c:forEach>
                            
                        </form:select>
                    </div>

                    <div class="select-list-date" id="select-date-container">
                        <h4>Target date</h4>
                        <form:input path="date" type="date" required="required"/>
                    </div>


                    <div class="add-edit-task-buttons">
                        <input id="add-edit-task-reset" onmouseup="handleDiscard()" value="" type="reset"/>
                        <input id="add-edit-task-submit" onmouseup="handleSubmit()" value="" type="submit"/>
                    </div>
                    
                </form:form>
    
            </div>
            <hr/>

            <footer>Designed and developed by Jon Arbell De Ocampo</footer>
        
        </div>

    </main>

    <div id="add-edit-list-container">
                
        <form:form modelAttribute="list" method="post">

            <sec:csrfInput/>
            
            <div id="list-input-container">

                <h3 class="add-edit-title"></h3>

                <form:input id="list-name" path="listName" maxlength="25" required="required" autofocus="autofocus" type="text" placeholder="List name"/>

            </div>

            <div id="add-edit-list-buttons">
                <input onmouseup="handleDiscard()" value="Discard" type="reset"/>
                <input onmouseup="handleSubmit()" value="Save" type="submit"/>
            </div>
            
        </form:form>
    </div>

    <script src="${pageContext.request.contextPath}/js/authenticated/buttonsFunctionalityTodoContainer/script.js"></script>

    <script src="${pageContext.request.contextPath}/js/authenticated/createNewItemModal/script.js"></script>

    <script src="${pageContext.request.contextPath}/js/authenticated/createNewTaskListContainer/script.js"></script>

    <script src="${pageContext.request.contextPath}/js/authenticated/handleSubmitAndResetTaskEdit/script.js"></script>
    
    <script src="${pageContext.request.contextPath}/js/authenticated/profileModal/script.js"></script>

    <script src="${pageContext.request.contextPath}/js/authenticated/sorting/homePage.js"></script>

    <script src="${pageContext.request.contextPath}/js/authenticated/goToHome/script.js"></script>

    <script src="${pageContext.request.contextPath}/js/authenticated/dynamicallyProgress/script.js"></script>

    <script src="${pageContext.request.contextPath}/js/authenticated/dateTime/script.js"></script>

</body>
</html>