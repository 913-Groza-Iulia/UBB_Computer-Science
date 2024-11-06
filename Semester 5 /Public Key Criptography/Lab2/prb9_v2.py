def decompose_n_minus_1(n):
    """
    This function decomposes n-1 into (2^s)*t with t odd
    """
    s = 0
    t = n - 1
    while t % 2 == 0:
        t //= 2
        s += 1
    return s, t

def modular_exponentiation(base, exponent, modulus):
    """
    This function calculates the modular exponentiation: (base^exponent) % modulus
    """
    result = 1

    if exponent == 0:
        return result

    c = base % modulus
    if exponent & 1:
        result = base

    exponent >>= 1
    while exponent > 0:
        c = (c * c) % modulus
        if exponent & 1:
            result= (c * result) % n
        exponent >>= 1

    return result


def is_strong_pseudoprime(n,b,s,t):
    """
    This function checks if n is a strong pseudoprime to a given base
    """
    b_t = modular_exponentiation(b,t,n)

    if b_t == 1:
        return True

    for j in range(s):
        b_2jt = modular_exponentiation(b, (2 ** j)*t,n)
        if b_2jt == n - 1:
            return True

    return False

def find_strong_pseudoprime_bases(n):
    """
    This function finds all bases for which n is a strong pseudoprime number
    """
    if n % 2 == 0 or n < 3:
        raise ValueError("n must be an odd composite number")
    s,t = decompose_n_minus_1(n)
    bases = []
    for b in range(1,n):
        if is_strong_pseudoprime(n,b,s,t):
            bases.append(b)
    return bases

n = 65
pseudoprime_bases = find_strong_pseudoprime_bases(n)
print(f"Strong pseudoprime bases for {n} are {pseudoprime_bases}")