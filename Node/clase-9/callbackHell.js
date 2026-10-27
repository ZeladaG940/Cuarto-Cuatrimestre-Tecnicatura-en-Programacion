function soyAsincrona(micallback) { // 1. Agregamos el parámetro
    setTimeout(function() { // 2. Quitamos el parámetro de aquí
        console.log("Hola, soy una funcion asincrona");
        micallback();
    }, 1000);
}

console.log("Iniciando el proceso...");

soyAsincrona(function() {
    console.log("Terminando el proceso...");
});


function hola(nombre, micallback) {
    setTimeout(function() {
        console.log("Hola " + nombre);
        micallback(nombre);
    }, 1000);
}

function hablar(callbackHablar){
    setTimeout(function(){
        console.log("bla bla bla bla");
        callbackHablar();
    }, 1000)
}
function adios(nombre, micallback) {
    setTimeout(function() {
        console.log("Adios " + nombre);
        micallback();
    }, 1500);
}

function conversacion(nombre, veces, callback){
    if(veces > 0){
        hablar(function(){
            conversacion(nombre, --veces, callback); // Corregido: 'callbacks' a 'callback'
        })
    } else{
        callback(); // Corregido: 'callback(nombre, callback)' a 'callback()'
    }
};
//--Proceso principal
console.log("Iniciando el proceso...");

hola("Orlando", function(nombre){
    conversacion(nombre, 4, function(){
        console.log("Terminando el proceso...");
    })
});

/*hola("Orlando", function(nombre){
    adios(nombre, function(){
        console.log("Terminamos");
    })
})*/
/*hola("Carlos", function(nombreRecibido) {
    hablar(function(){
        hablar(function() {
            hablar(function(){
                hablar(function(){
                    adios(nombreRecibido, function(){
                        console.log("Terminando el proceso..."); 

                    });
                })
            });
        });
    });
});*/

// adios('Carlos', function(){};); 
// adios('Carlos', function(){});