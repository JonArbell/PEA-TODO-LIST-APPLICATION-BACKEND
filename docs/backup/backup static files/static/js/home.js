//This function is to create a new Container and add a new todo
let isClickedAdd = false;
function addNewTodo() {
    
    if (!isClickedAdd) {
        if(isClickedEdit){
            alert('Please fill out all fields before clicking edit button.');
            
        }else {
            isClickedAdd = true;

            const noCustomContainer = document.querySelector('#no-custom');
            noCustomContainer.style.display = 'none';

            const formContainer = document.querySelector('.form-task-container');
            formContainer.style.display = 'flex';

            const formTitle = document.querySelector('.form-task-container h3');
            formTitle.textContent = 'Add New To-do:';
            
            const newUrl = `/pea/todo/add`;
            window.history.pushState({path:newUrl},'',newUrl);
            
            const editForm = document.querySelector('#edit-task-form');
            editForm.action = `/pea/todo/add`;

        }

    } else {
        alert('Please fill out all fields before clicking Add New button.');
    }
}

function createNew(event){

    const buttonsModal = document.querySelector('#create-new-button-modal-container');
    const createNewButtonContainer = document.querySelector('.create-new-button');

    if(createNewButtonContainer.contains(event.target)){

        if(isClickedCreateNew){
            notClickedCreateNew(buttonsModal);
            return;
        }

        clickedCreateNew(buttonsModal);
    }else{
        notClickedCreateNew(buttonsModal);
    }

}
document.addEventListener('click',createNew);

let isClickedCreateNew = false;

function clickedCreateNew(buttonsModal){
    buttonsModal.style.display = 'flex';
    isClickedCreateNew = true;
}

function notClickedCreateNew(buttonsModal){
    buttonsModal.style.display = 'none';
    isClickedCreateNew = false;
}

function handleDelete(){

    document.querySelector('#confirm-delete-modal').style.display = 'flex';
    document.querySelectorAll('.big-two').forEach(container =>{
        container.classList.add('background-confirm-modal');
    });;
}
function confirmButton(){
    document.querySelector('#delete-form').submit();
}
function cancelButton(){
    document.querySelector('#confirm-delete-modal').style.display = 'none';
    document.querySelectorAll('.big-two').forEach(container =>{

        container.classList.remove('background-confirm-modal');
    });;
}


function markAsComplete(){

}


//This function is for handle a message of Delete Todo
function handleDeleteMessage(message) {

    if (message === 'delete') {
        successMessagePopUpAnimate('To-do item deleted successfully!');
    }else if(message.length > 6){

        if(message.includes('request')){
            alert(`Todo item deletion failed!\n\n ${message}`);
        }else{
            alert(message);
        }

    }
}

function successMessagePopUpAnimate(message){
    const popupMessageContainer = document.getElementById('popup-message');
    popupMessageContainer.style.display = 'flex';
    popupMessageContainer.classList.add('popup-hidden');

    const paragraph = document.querySelector('#popup-message p');
    paragraph.textContent = message;

    setTimeout(()=>{
        paragraph.textContent = '';

        popupMessageContainer.style.display = 'none';
        popupMessageContainer.classList.remove('popup-hidden');
    },7000);
}


//This function is for handle a message of add New Todo
function handleAddTodoMessage(message){

    if (message === 'add') {
        successMessagePopUpAnimate('To-do added successfully!');

    }else if(message.length > 3){
        alert(`Adding new todo failed!\n\n${message}`);
    }
}

let isClickedProfile = false;
//This function is for Modal of Profile 
function profile(event) {
    const image = document.getElementById('profile_logo');

    if(image.contains(event.target)){
        
        if(isClickedProfile){
            notClickedProfile();
            return;
        }
        clickedProfile();
    }else{
        notClickedProfile();
    }

}
document.addEventListener('click',profile);

function clickedProfile(){
    
    document.querySelector('#modal-profile').style.visibility = 'visible';
    isClickedProfile = true;
}
function notClickedProfile(){

    document.querySelector('#modal-profile').style.visibility = 'hidden';
    isClickedProfile = false;
}




//This function is for returning to pending page
function pending() {
    const elements = document.getElementsByClassName('pending');

    for (let i = 0; i < elements.length; i++) {
        elements[i].addEventListener('click', (event) => {
            event.preventDefault();
            window.location.href = '/pea/pending';
        });
    }
}
pending();


//This function is for edit a todo
let isClickedEdit = false;
function editButton(idNum,title,description,date,done,listsTodoId) {

    if(!isClickedEdit){

        if(isClickedAdd){
            alert('Please fill out all fields before clicking edit button.');
           
        }else{
            isClickedEdit = true;

            const noCustomContainer = document.querySelector('#no-custom');
            noCustomContainer.style.display = 'none';

            const formContainer = document.querySelector('.form-task-container');
            formContainer.style.display = 'flex';
            
            const newUrl = `/pea/todo/edit?title=${title}`;
            window.history.pushState({path:newUrl},'',newUrl);
            
            const editForm = document.querySelector('#edit-task-form');
            editForm.action = `/pea/todo/edit?id=${idNum}`;

            const titleField = document.querySelector(`.title`);
            const descriptionField = document.querySelector(`.description`);
            const dateField = document.querySelector(`.edit-date`);

            titleField.value = `${title}`;
            descriptionField.value = `${description}`;
            dateField.value = `${date}`;
        }

        
    }else{
        alert('Please fill out all fields before clicking another edit button.');
    }


}


function cancelEdit(){
    
    window.location.href = '/pea/pending';
}



//These functions is for change the url for sorting and change the selectedIndex base on Sort By

function sorting(){
    const form = document.getElementById('sort-form');
    form.setAttribute('action','/pea/pending/sort');
    form.submit();

}
function sortBy(){
    const sortByTitle = 'http://localhost:8080/pea/pending/sort?sort=Title';
    const sortByTargetDate = 'http://localhost:8080/pea/pending/sort?sort=Target+Date';
    const sortByDateModified = 'http://localhost:8080/pea/pending/sort?sort=Date+Modified';

    const currentWindow = window.location.href;

    const select = document.getElementById('sort');

    if(currentWindow == sortByTitle){
        select.selectedIndex = 1;
        
    }else if(currentWindow == sortByTargetDate){
        select.selectedIndex = 2;
        
    }else if(currentWindow == sortByDateModified){
        window.location.href = '/pea/pending';
        
    }
}
sortBy();


function handleEditTodoMessage(edit){
    
    if(edit === 'success'){
        successMessagePopUpAnimate('To-do item successfully edited!');

    }else if(edit.length > 7){
        alert(edit);
    }
    
}

function date(){
    const now = new Date();

    let getMonth;

    switch(now.getMonth()){
        case 0:
            getMonth = 'January';
            break;
        case 1:
            getMonth = 'February';
            break;
        case 2:
            getMonth = 'March';
            break;
        case 3:
            getMonth = 'April';
            break;
        case 4:
            getMonth = 'May';
            break;
        case 5:
            getMonth = 'June';
            break;
        case 6:
            getMonth = 'July';
            break;
        case 7:
            getMonth = 'August';
            break;
        case 8:
            getMonth = 'September';
            break;
        case 9:
            getMonth = 'October';
            break;
        case 10:
            getMonth = 'November';
            break;
        case 11:
            getMonth = 'December';
            break;
        
    }

    const dayOfMonth = now.getDate();
    const getYear = now.getUTCFullYear();

    let day;

    switch(now.getDay()){
        case 1:
            day = 'Monday';
            break;
        case 2:
            day = 'Tuesday';
            break;
        case 3:
            day = 'Wednesday';
            break;
        case 4:
            day = 'Thursday';
            break;
        case 5:
            day = 'Friday';
            break;
        case 6:
            day = 'Saturday';
            break;
        case 7:
            day = 'Sunday';
            break;
    }
    
    const finalForm = `${getMonth} ${dayOfMonth}, ${getYear} ${day}`;

    document.querySelector('#date-calendar').textContent = finalForm;
}
date();

setInterval(date,86400000);

function clock(){

    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    const seconds = now.getSeconds().toString().padStart(2, '0');

    const amOrpm = hours < 12 ? "AM" :  "PM";

    const time = `${hours}:${minutes}:${seconds} ${amOrpm}`;

    document.querySelector('#clock').textContent = time;

}

clock();

setInterval(clock,1000);

// function addNewList(){
//     const addNewButton = document.querySelector('#add-new-list');
//     const container = document.querySelector('#lists-of-tasks');
//     addNewButton.addEventListener('click',(event)=>{

//         event.preventDefault();

//         const createAnchor = document.createElement('a');
//         createAnchor.href = '/pea/create/list';
//         createAnchor.innerHTML = `<li>Lopit</li>`;
        
        
//         container.appendChild(createAnchor);
//     });
// }
// addNewList();