def mod_exp(base, exp, mod):
    result = 1
    steps = []
    while exp > 0:
        if exp % 2 == 1:
            result = (result * base) % mod
        base = (base * base) % mod
        exp //= 2
    return result

n = 3017
s = 3 # value of s
t = 377  # value of t

bases = [2, 3, 5]

results = {
    "2^(2^0)": mod_exp(2, 2**0, n),
    "2^(2^1)": mod_exp(2, 2**1, n),
    "2^(2^2)": mod_exp(2, 2**2, n),
    "2^(2^3)": mod_exp(2, 2**3, n),
    "2^(2^4)": mod_exp(2, 2**4, n),
    "2^(2^5)": mod_exp(2, 2**5, n),
    "2^(2^6)": mod_exp(2, 2**6, n),
    "2^(2^7)": mod_exp(2, 2**7, n),
    "2^(2^8)": mod_exp(2, 2**8, n),
    "2^(2^9)": mod_exp(2, 2**9, n),
    "2^t": mod_exp(2, t, n),
    "2^(2*t)": mod_exp(2, 2 * t, n),
    "2^(2^2*t)": mod_exp(2, 2**2 * t, n),
    "2^(2^3*t)": mod_exp(2, 2**3 * t, n),
    "2^(2^4*t)": mod_exp(2, 2**4 * t, n),
    "3^t": mod_exp(3, t, n),
    "3^(2*t)": mod_exp(3, 2 * t, n),
    "3^(2^2*t)": mod_exp(3, 2**2 * t, n),
    "3^(2^3*t)": mod_exp(3, 2**3 * t, n),
    "3^(2^4*t)": mod_exp(3, 2**4 * t, n),
    "5^t": mod_exp(5, t, n),
    "5^(2*t)": mod_exp(5, 2 * t, n),
    "5^(2^2*t)": mod_exp(5, 2**2 * t, n),
    "5^(2^3*t)": mod_exp(5, 2**3 * t, n),
    "5^(2^4*t)": mod_exp(5, 2**4 * t, n),
}

for key, value in results.items():
    print(f"{key} % {n} = {value}")
