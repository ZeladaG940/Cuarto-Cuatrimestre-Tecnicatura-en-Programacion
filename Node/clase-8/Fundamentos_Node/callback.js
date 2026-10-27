function soyAsincrona(){
    setTimeout(function(micallback){
        console.log("Hola, soy una funcion asincrona");
        micallback();
    }, 1000)
}

console.log("Iniciando el proceso...");

soyAsincrona(function(){
    console.log("Terminando el proceso...");
});



function hola(nombre, micallback){
    setTimeout(function(){
        console.log("Hola " + nombre);
        micallback();
    }, 1000)
}

function adios(nombre, micallback){
    setTimeout(function(){
        console.log("Adios " + nombre);
        otroCallback();
    }, 1000)
}

console.log("Iniciando el proceso...");
hola("Calos", function(nombre){
    adios(nombre + function(){
        console.log("Terminando el proceso...");
    })
})

//hola('Carlos', function(){});
//adios('Carlos', function(){};)