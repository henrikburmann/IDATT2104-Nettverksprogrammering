document.getElementById("form").addEventListener("submit", function (event){
    event.preventDefault();

    let code = document.getElementById("kode").value;

    const url = "http://localhost:8080/compile"
    const request = new XMLHttpRequest()

    request.open("POST", url)

    request.setRequestHeader("content-type", "application/json; charset=utf-8")

    request.responseType = "json"

    request.send(JSON.stringify({"code": code}))

    request.onload = () => {
        // document.getElementById("utskrevetKode").value = request.response.value["code"]
        console.log(request.response.text)
        console.log(request.valueOf())
        console.log(request.response.value["code"])
        console.log(request.response.text())
        console.log(request.response.output)
    }


    console.log(code);

})
