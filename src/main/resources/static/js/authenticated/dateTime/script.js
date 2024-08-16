function date(){

    const date = new Date();

    const year = date.getFullYear();

    let getMonth;

    switch(date.getMonth()){
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

    const day = date.getDate();

    document.querySelector('#date').textContent = `${getMonth} ${day}, ${year}`;


}

date();
setInterval(date,1000);


function clock(){

    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    const seconds = now.getSeconds().toString().padStart(2, '0');

    const amOrpm = hours < 12 ? "AM" :  "PM";

    const time = `${hours}:${minutes}:${seconds} ${amOrpm}`;

    document.querySelector('#time').textContent = time;

}

clock();

setInterval(clock,1000);
