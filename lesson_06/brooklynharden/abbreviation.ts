
function isValidAlphaAbbreviation(word:string, abbreviation:string):boolean{
    let i:number = 0; //keep the index of the word
    for(let j=0; j<abbreviation.length;j++){
        const abbrChar=abbreviation[j]; 

         // We add 1 to shift the range, 'a'.charCodeAt(0) - 'a'.charCodeAt(0) = 0 ,
         // because 97-97 = 0. 
         // Adding the '1' shifts it to a===1, which is what we want.
        
        const skip =abbrChar.charCodeAt(0) - 'a'.charCodeAt(0) + 1 ; 
        if(word[i] === abbrChar){ 
            i++; //if the current word letter and the current abbrchar match match go to the next letter
        }else{
            i+=skip; // Otherwise, treat abbrChar as a letter abbreviation and skip that many characters
        }
    }
    return i === word.length;
}

console.log(isValidAlphaAbbreviation("substitution", "sjn")); //true
console.log(isValidAlphaAbbreviation("test", "ab")); //false
