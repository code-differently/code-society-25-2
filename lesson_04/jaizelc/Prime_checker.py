def is_prime(number):

    if number < 2:
        return False
    
    for i in range(2, number):
       
        if number % i == 0:
            return False
    
    return True


print("Is 2 prime?", is_prime(2))
print("Is 4 prime?", is_prime(4))
print("Is 7 prime?", is_prime(7))
