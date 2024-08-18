//These functions are for view and close the todo details
let isViewClick = false;
function view(todoTitle,todoDone,todoDescription,todoListName,todoDate){

    if(isViewClick){
        
        return;
    }

    document.querySelector(`.view-details`).style.display = 'flex';
    
    document.querySelector('#view-details-title').textContent = `Title : ${todoTitle}`;

    document.querySelector('#view-details-description p').textContent = todoDescription != '' ? `${todoDescription}` : 'No Description';

    document.querySelector('#view-details-list p').textContent = `${todoListName}`;

    document.querySelector('#view-details-target-date p').textContent = `${todoDate}`;

    document.querySelector('#view-details-done p').textContent = todoDone == 'false' ? 'No' : 'Yes' ;

    blur();

    isViewClick = true;
}



document.addEventListener('mousedown',close);

function close(event){
    
    const close = document.querySelector('.closeContainer');

    if(close.contains(event.target)){
        
        document.querySelector('.view-details').style.display = 'none';
        removeBlur();
        isViewClick = false;
    }

}

