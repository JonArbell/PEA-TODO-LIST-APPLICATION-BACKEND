export const failedMessage = async (message) => {

    const promptMessage = document.querySelector('#prompt-message');

    promptMessage.classList.remove('success-message');
    promptMessage.classList.remove('failed-message');
    promptMessage.textContent = '';
    promptMessage.style.display = 'none';
    
    promptMessage.textContent = `${message}`;
    promptMessage.style.display='flex';
    promptMessage.offsetHeight;
    promptMessage.classList.add('failed-message');

}

export const successMessage = async (message) => {

    const promptMessage = document.querySelector('#prompt-message');

    promptMessage.classList.remove('success-message');
    promptMessage.classList.remove('failed-message');
    promptMessage.textContent = '';
    promptMessage.style.display = 'none';
    
    promptMessage.textContent = `${message}`;
    promptMessage.style.display='flex';
    promptMessage.offsetHeight;
    promptMessage.classList.add('success-message');

}