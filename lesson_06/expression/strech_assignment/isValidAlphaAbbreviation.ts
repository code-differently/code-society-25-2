// understanding the first letter is the first letter of abbreviation will be the first letter of the actual word. we can chceck if the next letters are apart of the word or abbreviation 
// assumptions - if the abbreviation does not start with the first letter of the word, it is not valid? can the inital value be apart of the abbreviation? if so t
// hen the logic is to check if the first letter of the abbreviation is the first letter of the word, then check if the next letters are apart of the word or abbreviation.
/* 
  check to see if the starting letter of the word is the same as the start of the abbrevation same with the end if false return false
  check to see if the abbrevation is apart of the word or the abreavation create a mapping of letters of characters
*/

function isValidAlphaAbbreviation(word: string, abbreviation: string): boolean {
    // checking to see if the first and last letters of the abbrevation match the actual word
    if (word.charAt(0) != abbreviation.charAt(0) || word.charAt(word.length-1) != abbreviation.charAt(abbreviation.length-1)) {
        return false

    }
    // index of the word varaible
    let wordPointer = 1;

    for (let abrevPointer = 1;abrevPointer < abbreviation.length;abrevPointer++) {

        // check to see if the abbreavation char is apart of the word or if its the abbreavation formula
        if (word.charAt(wordPointer) != abbreviation.charAt(abrevPointer)) {
        
         // increment by the amount of the correspodning character value
         let num_to_letter_conversion = abbreviation.charCodeAt(abrevPointer) - 96;

         wordPointer += num_to_letter_conversion;
          
        } else {
            wordPointer++;
        }

        
    }



    
    return wordPointer === word.length;
    

}


console.log(isValidAlphaAbbreviation("test", "ab"));