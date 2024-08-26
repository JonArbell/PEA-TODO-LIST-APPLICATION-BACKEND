//List Success Failed Message Handlers

function handleGetListErrorMessage(getListError){

    const promptMessage = document.querySelector('#prompt-message');

    if(getListError != ''){

        promptMessage.textContent = `${getListError}`;
        promptMessage.classList.add('failed-message');
        promptMessage.style.display = 'flex';

        setTimeout(()=>{
            promptMessage.textContent = '';
            promptMessage.classList.remove('failed-message');
            promptMessage.style.display = 'none';
        },7000);

    }
    

}


function handleCreateListMessage(message){
    const promptMessage = document.querySelector('#prompt-message');

    if(message != ''){

        if(message === 'success'){
            promptMessage.textContent = 'List added successfully!';
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

function handleEditListMessage(message){
    const promptMessage = document.querySelector('#prompt-message');
    if(message != ''){

        if(message === 'success'){
            promptMessage.textContent = 'Listname successfully changed!';
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

function handleDeleteListMessage(message){
    const promptMessage = document.querySelector('#prompt-message');

    if(message != ''){

        if(message === 'success'){
            promptMessage.textContent = 'List item deleted successfully!';
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

