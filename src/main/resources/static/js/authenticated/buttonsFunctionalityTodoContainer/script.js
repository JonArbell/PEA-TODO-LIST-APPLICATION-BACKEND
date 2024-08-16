//Add functionality to all buttons in a todo container 
document.addEventListener('mousedown',addBounceClickAllButton);
document.addEventListener('mouseup',handleClickAllButton);
function handleClickAllButton(event){

    const buttons = document.querySelectorAll('#task-buttons button');

    buttons.forEach(button =>{

        if(button.contains(event.target)){

            button.style.transform = 'scale(1)';
            button.style.filter = 'brightness(100%)';

        }

    });

}
function addBounceClickAllButton(event){
    const buttons = document.querySelectorAll('#task-buttons button');

    buttons.forEach(button =>{

        if(button.contains(event.target)){

            button.style.transform = 'scale(0.95)';
            button.style.filter = 'brightness(85%)';

        }

    });

}

addHoverAllButton();
function addHoverAllButton(){

    const buttons = document.querySelectorAll('#task-buttons button');

    buttons.forEach(button =>{

        button.addEventListener('mouseover',()=>{

            button.style.filter = 'brightness(130%)';

        });
    });

    buttons.forEach(button =>{

        button.addEventListener('mouseout',()=>{
            button.style.filter = 'brightness(100%)';

        });
    });

}


function deleteTodoItem(todoId){

    const form = document.querySelector(`#form-${todoId}`); 

    if (form) {
        form.action = `/pea/todo/delete/${todoId}`;
        form.method = 'post';
        form.submit();
    } else {
        console.error('Form not found');
    }

}

function markAsCompleteItem(todoId){

    const form = document.querySelector(`#form-${todoId}`); 

    if(form){
        form.method = 'post';
        form.action = `/pea/todo/${todoId}/mark-as-done`;
        form.submit();
    }else {
        console.error('Form not found');
    }


}



