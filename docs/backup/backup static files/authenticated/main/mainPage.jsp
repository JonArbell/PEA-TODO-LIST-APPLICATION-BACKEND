<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta name="csrf-token" content="${_csrf.token}"/>
    
    <link rel="icon" href="<%= request.getContextPath() %>/images/checklist.ico" type="icon">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">

    <title>${title}</title>
</head>
<body>

    <div id="popup-message">
        <p></p>
    </div>

    <div id="confirm-delete-modal">
        <p>Are you sure you want to delete this to-do item? This action cannot be undone.</p>

        <div id="confirm-delete-buttons-container">
            <button class="confirm-delete-buttons" onclick="confirmButton()">Confirm</button>
            <button class="confirm-delete-buttons" onclick="cancelButton()">Cancel</button>
        </div>

    </div>

    <nav id="top-nav-container" class="container-only big-two">
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
                    <img class="nav-button" id="profile_logo" src="<%= request.getContextPath() %>/images/try_profile_logo.ico"/>
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

    

    <main id="main" class="container-only big-two">

        <div id="left-nav-div">

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

    
        <div class="list-todo container-only" id="list-todo">
  
            <div id="todo-list-container">

                <c:choose>

                    <c:when test="${empty todos}">
                        <h1 class="empty">No tasks yet! Click on 'Create new' to get started!</h1>
                    </c:when>

                    <c:otherwise>
                        
                        <div class="title-sort-container">

                            <div id="panis">
                                <div id="main-title">

                                    <h2>Pending Tasks</h2>
        
                                </div>
    
                                <h5 id="sort-container">
                        
                                    <form id="sort-form" method="get">
                                        <label for="sort">Sort by:</label>
                                        <select class="select" onchange="sorting()" id="sort" name="sort">
                            
                                            <option class="select" value="Date Modified">Date Modified</option>
                                            <option class="select" value="Title">Title</option>
                                            <option class="select" value="Target Date">Target Date</option>
                                        </select>
                                    </form>
                        
                                </h5>
    
                                <hr/>
                            </div>
                            <h3 id="todo-list-title">TO-DO LIST:</h3>
                        </div>

                        <div id="all-todo-list">
                            


                            <c:forEach items="${todos}" var="todo">
                                <div class="todo container" id="todo-${todo.id}">
            
                                    <form:form modelAttribute="todo" id="edit-todo-form-${todo.id}" class="edit-todo-form" action="/pea/edit/todo?id=${todo.id}">
            
                                        <sec:csrfInput />
                    
                                        <h3>Edit to-do</h3>
                    
                                        <br/>
                                        <form:input path="id" value="${todo.id}" type="hidden" required="required"/>
                    
                                        
                                        <form:input id="edit-title" value="${todo.title}" path="title" type="text" required="required" maxlength="35"/>
                    
                                        <br/>
                    
                                        <form:input id="edit-date" value="${todo.date}" path="date" type="date" required="required"/>
                    
                                        <div id="edit-buttons">
                    
                                            <input id="cancel-edit-button" type="reset" value="Cancel" class="new-submit" onclick="cancelEdit()"/>
                    
                                            <input value="Save changes" type="submit" class="new-submit"/>
                    
                                        </div>
                    
                                        
                    
                                    </form:form>
                                    
                                    <div class="all-data" id="all-data-${todo.id}">
            
                                        <a href="/pea/task/${todo.id}">
                                            <h3 class="view-todo-item">View Details</h3>
                                        </a>
                                        
                                        <table class="table">
                                        
                                            <tr>
            
                                                
                                                <br/>
            
            
                                                <th>Title</th>
                                                <th>Target Date</th>
                                                <th>Completed</th>
                                                
                                            </tr>
                        
                                            <tr>
                                                <td colspan="3">
                                                    <hr/>
                                                </td>
                                            </tr>
                        
                                            <tr>
                                                <div>
                                                    <td id="desc">${todo.title}</td>
                                                    <td>${todo.formattedDate}</td>
    
                                                    <c:choose>
                                                        <c:when test="${todo.done}">
                                                            <td>Yes</td>
                                                        </c:when>
    
                                                        <c:otherwise>
                                                            <td>No</td>
                                                        </c:otherwise>
                                                    </c:choose>
    
                                                </div>
                                                
                                            </tr>
                                            
                                            <tr>
                                                <td colspan="3">
                                                    <hr>
                                                </td>
                                            </tr>
                        
                                        </table>
                        
                                        <div id="edit-delete-container">
 
                                            <div id="delete-container">
                                                
                                                <form id="delete-form" method="post" action="/pea/todo/delete/${todo.id}">
            
                                                    <sec:csrfInput />
                                                </form>
                                                <button onclick="handleDelete()" id="delete">Delete</button>
                                            </div>
    
                                            <div id="mark-as-complete-container">
    
                                                <form action="/pea/todo/${todo.id}/mark-as-done">
                                                    <button onclick="markAsComplete()" class="btn-complete" type="submit">
                                                        <span class="icon-check">&#10003;</span> Mark as Complete
                                                    </button>
                                                </form>
    
                                                
                                            </div>
                        
                                            <div id="edit-container">
                                                <a onclick="editButton(`${todo.id}`,`${todo.title}`,`${todo.shortDescription}`,`${todo.date}`,`${todo.done}`,`${todo.listsTodoId}`)">
                                                    <img id="edit" src="<%= request.getContextPath() %>/images/edit.png" alt="edit.png"/>
                                                    
                                                </a>
                                                
                                            </div>
                        
                                        </div>
                                        
                                    </div>
                                        
                                    
                                </div>    
                                
                    
                                                
                    
                            </c:forEach>
                        </div>

                    </c:otherwise>

                </c:choose>


                <div id="create-new-button-modal-container">

                    <h5>Create New Item</h5>

                    <div id="create-new-buttons-modal-container">
                        <button class="create-new-buttons-modal" id="add-new-todo-button" onclick="addNewTodo()">To-do</button>
                        <button class="create-new-buttons-modal" id="add-new-list-button" onclick="addNewList()">List</button>
                    </div>

                    
                </div>
                
                <div class="create-new-button" id="create-button">
                    <button id="create-new-button">Create New</button>
                </div>

            </div>
            
        </div>

        <div id="right-nav-div" class="container-only">
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

                
                <div id="edit-add-task-list-container" class="form-task-container">
                    <h3>Edit To-do:</h3>
                    <form:form modelAttribute="todo" id="edit-task-form" action="#" method="post">
                        
                        <section class="text-field">
                            <form:input required="required" path="title" id="edit-title" class="title" type="text" placeholder="Title"/>
                            <form:textarea path="shortDescription" class="description" placeholder="Description" rows="7"></form:textarea>
                        </section>

                        <section class="list-date-field">

                            <div class="list-field">
                                <label class="label">List</label>
                                <select>
                                    <option>None</option>
                                    <option>Personal</option>
                                    <option>Work</option>
                                </select>
                            </div>
                            <div class="date-field">
                                <label class="label">Target Date</label>
                                <form:input required="required" class="edit-date" path="date" type="date"/>
                            </div>
                            
                        </section>

                        <section class="cancel-submit-field">
                            <input onclick="cancelEdit()" class="cancel-button" type="reset" value="Discard Changes"/>
                            <input type="submit" value="Save Changes"/>
                        </section>

                    </form:form>
                    


                </div>
                
                <!-- <img id="logo" src="<%= request.getContextPath() %>/images/checklist.png" alt="logo.png"/> -->
    
                <hr class="last-hr"/>
                <footer>
                    Designed and developed by Jon Arbell De Ocampo
                </footer>
            </nav>
        </div>
        
    

    </main>


    <script src="${pageContext.request.contextPath}/js/home.js"></script>
        
    <script>
        //This code is for showing to the user that the todo is successfully deleted
        const deleteTodoMessage = "${deleteTodoMessage}";
        handleDeleteMessage(deleteTodoMessage);


        //This code is for showing to the user that the todo is successfully edited
        const editTodoMessage = '${editTodoMessage}';
        handleEditTodoMessage(editTodoMessage);


        //This code is for showing to the user that the todo is successfully added
        const addTodoMessage = '${addTodoMessage}';
        handleAddTodoMessage(addTodoMessage);

    </script>

</body>
</html>