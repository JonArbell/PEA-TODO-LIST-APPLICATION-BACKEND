
//Todo Success Failed Message Handlers

function handleEditTodoMessage(edit){

    const promptMessage = document.querySelector('#prompt-message');

    if(edit != ''){
        if(edit === 'success'){
            promptMessage.textContent = 'To-do item successfully edited!';
            promptMessage.classList.add('success-message');
            promptMessage.style.display = 'flex';

            setTimeout(()=>{
                promptMessage.textContent = '';
                promptMessage.classList.remove('success-message');
                promptMessage.style.display = 'none';
            },7000);
    
        }else if(edit.length > 7){
            promptMessage.textContent = `${edit}`;
            promptMessage.classList.add('failed-message');
            promptMessage.style.display = 'flex';

            setTimeout(()=>{
                promptMessage.textContent = '';
                promptMessage.classList.remove('failed-message');
                promptMessage.style.display = 'none';
            },7000);
        }
    }
    
}

function handleAddTodoMessage(message){

    const promptMessage = document.querySelector('#prompt-message');

    if(message != ''){
        if (message === 'add') {
            promptMessage.textContent = 'To-do added successfully!';
            promptMessage.classList.add('success-message');
            promptMessage.style.display = 'flex';

            setTimeout(()=>{
                promptMessage.textContent = '';
                promptMessage.classList.remove('success-message');
                promptMessage.style.display = 'none';
            },7000);
        }else if(message.length > 3){
            promptMessage.textContent = `${message}`;
            promptMessage.classList.add('failed-message');
            promptMessage.style.display = 'flex';

            setTimeout(()=>{
                promptMessage.textContent = '';
                promptMessage.classList.remove('failed-message');
                promptMessage.style.display = 'none';
            },7000);
        }
    }

}

function handleDeleteMessage(message) {

    const promptMessage = document.querySelector('#prompt-message');

    if(message != ''){

        if (message === 'delete') {
            promptMessage.textContent = 'To-do item deleted successfully!';
            promptMessage.classList.add('success-message');
            promptMessage.style.display = 'flex';

            setTimeout(()=>{
                promptMessage.textContent = '';
                promptMessage.classList.remove('success-message');
                promptMessage.style.display = 'none';
            },7000);
        }else if(message.length > 6){
            
            if(message.includes('request')){
                alert(`Todo item deletion failed!\n\n ${message}`);
            }else{
                promptMessage.textContent = `${message}`;
                promptMessage.classList.add('failed-message');
                promptMessage.style.display = 'flex';
    
                setTimeout(()=>{
                    promptMessage.textContent = '';
                    promptMessage.classList.remove('failed-message');
                    promptMessage.style.display = 'none';
                },7000);
            }

        }

    }

}

function handleMarkAsCompleteMessage(message){
    const promptMessage = document.querySelector('#prompt-message');

    if(message != ''){

        if(message === 'success'){
            promptMessage.textContent = 'To-do item marked as complete successfully!';
            promptMessage.classList.add('success-message');
            promptMessage.style.display = 'flex';

            setTimeout(()=>{
                promptMessage.textContent = '';
                promptMessage.classList.remove('success-message');
                promptMessage.style.display = 'none';
            },7000);
        }else{
            promptMessage.textContent = `${message}`;
            promptMessage.classList.add('failed-message');
            promptMessage.style.display = 'flex';

            setTimeout(()=>{
                promptMessage.textContent = '';
                promptMessage.classList.remove('failed-message');
                promptMessage.style.display = 'none';
            },7000);
        }

    }

}


