
def is_leap_year(y):
    if (y % 4 == 0 and y % 100 != 0) or y % 400 == 0:
        return True
    else:
        return False


def days_in_year(y):
    if is_leap_year(y):
        return 366
    else:
        return 365


def days_in_a_month(y, m):
    if m == 1 or m == 3 or m == 5 or m == 7 or m == 8 or m == 10 or m == 12:
        return 31
    elif m == 2:
        if is_leap_year(y):
            return 29
        else:
            return 28
    else:
        return 30


def days_between_dates(y1, m1, d1, y2, m2, d2):
    days = 0
    while y1 < y2 or (y1 == y2 and m1 < m2) or (y1 == y2 and m1 == m2 and d1 < d2):
        days += 1
        d1 += 1

        if d1 > days_in_a_month(m1, y1):
            d1 = 1
            m1 += 1

        if m1 > 12:
            m1 = 1
            y1 += 1

    return days


def age_in_days(b_y, b_m, b_d, c_y, c_m, c_d):
    age = days_between_dates(b_y, b_m, b_d, c_y, c_m, c_d)
    return age


def main():
    b_y = int(input("Please introduce the birth year: "))
    b_m = int(input("Please introduce the birth month: "))
    b_d = int(input("Please introduce the birth day: "))

    c_y = int(input("Please introduce the current year: "))
    c_m = int(input("Please introduce the current month: "))
    c_d = int(input("Please introduce the current day: "))
    sol = age_in_days(b_y, b_m, b_d, c_y, c_m, c_d)
    print("The person is", sol, "days old")


main()
