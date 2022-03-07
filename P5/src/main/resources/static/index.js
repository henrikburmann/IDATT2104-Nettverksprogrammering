document.getElementById("form").addEventListener("submit", function (event){
    event.preventDefault();

    let code = document.getElementById("kode").value;

    const url = "http://localhost:8080/compile"
    const request = new XMLHttpRequest()

    fetch(url, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({"code": code})
    }).then(response => response.text())
        .then(text => document.getElementById("utskrevetKode").innerHTML = text);
})
