

export function viewDetails(){
    document.querySelector('#view-details').style.display = 'flex';
    document.querySelector('#modal-view-background').style.display = 'block';
}

export function removeDetails(){
    document.querySelector('#view-details').style.display = 'none';
    document.querySelector('#modal-view-background').style.display = 'none';
}