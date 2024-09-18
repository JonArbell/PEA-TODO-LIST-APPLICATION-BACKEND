export const loadProfile = async (userResponse) =>{
    const fullName = `${userResponse.firstName} ${userResponse.lastName}`;
    document.querySelector('#profile-full-name').textContent = `${fullName}`;
    document.querySelector('#profile-modal-username').setAttribute('href',`/profile/${userResponse.username}`);
}

export const showProfileModal = () =>{
    document.querySelector('#profile-modal').style.display = 'flex';
}

export const hideProfileModal = () =>{
    document.querySelector('#profile-modal').style.display = 'none';
}