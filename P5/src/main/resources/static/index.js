document.getElementById("form").addEventListener("submit", function (event){
    event.preventDefault();
    console.log("mottatt")
    let code = document.getElementById("kode").value;

    const url = "http://localhost:8080/compile"
    const request = new XMLHttpRequest()

    request.open("post", url)
    request.setRequestHeader("content-type", "application/json; charset=utf-8")
    request.responseType = "json"
    request.onload = () => {
        document.getElementById("output").value = request.response.output
    }
    request.send(JSON.stringify({"code":code}))

    console.log(code);
    document.querySelector("#utskrevetKode").innerHTML = code;
})
