//  Quotes import
document.querySelector('footer > form').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const file = document.querySelector('footer > form > input').files[0];

    if (!file) {
        alert("No file detected");
        return
    }

    const formData = new FormData();
    formData.append('file', file);

    try {
        const response = await fetch(`/api/quotes`, {
            method: 'POST',
            body: formData
        });

        if (response.ok) {alert('Import successful')}
        else {alert('Import failed')}
    } catch (err) {
        alert('Error' + err.message)
    }
})

// Table reinitialization
document.getElementById("reinit-btn").addEventListener('click', async () => {
    
    try {
        const res = await fetch(`/api/quotes/reinit`, {method: 'POST'});

        if (res.ok) {alert('Reinitialization successful')}
        else {alert('Reinitialization failed')}
    } catch (err) {
        alert('Error' + err.message)
    }

})

// All the rest below this line is the showing table function
document.getElementById('window-btn2').disabled = true;

document.getElementById('window-btn1').addEventListener('click', async () => {
    const res = await fetch(`/api/quotes/all`);
    const quotes = await res.json();

    quotes.forEach(quote => {
        const li = document.createElement('li');
        li.textContent = JSON.stringify(quote);
        document.querySelector('#window > ol').appendChild(li);
    })

    document.getElementById('window').style.display = 'block';
    document.getElementById('window-btn1').disabled = true;
    document.getElementById('window-btn2').disabled = false;
})

document.getElementById('window-btn2').addEventListener('click', () => {
    document.getElementById('window').style.display = 'none';
    document.querySelector('#window > ol').innerHTML = '';
    document.getElementById('window-btn1').disabled = false;
    document.getElementById('window-btn2').disabled = true;
})