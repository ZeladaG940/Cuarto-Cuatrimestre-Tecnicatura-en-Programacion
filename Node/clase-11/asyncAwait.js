async function hola(nombre, micallback) {
    return new Promise(function(resolve, reject){
            setTimeout(function() {
                console.log("Hola " + nombre);
                resolve(nombre);
        }, 1000);
    })
}

async function hablar(nombre){
    return new Promise((resolve, reject) =>{
        setTimeout(function(){
            console.log("bla bla bla bla");
            resolve();
        }, 1000)
    })
}
async function adios(nombre) {
    return new Promise((resolve, reject)=>{
        setTimeout(function() {
            console.log("Adios " + nombre);
            resolve();
            //reject("Hay un error")
        }, 1000);
    })
}

async function main() {
    let nombre = await hola("Orlando");
    await hablar();
    await hablar();
    await hablar();
    await adios(nombre);
}

console.log("Empezamos el proceso...");
main()
console.log("Esta va ser la segunda instruccion");

//El codigo en ingles es una mejor practica
function sayHello(name){
    return new Promise((resolve, reject)=>{
        setTimeout(()=>{
            console.log("Hello " + name);
            resolve(name);
        }, 1000)
    })
}