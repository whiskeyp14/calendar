"""
Source Code   : Tugas2-1606918055.py
Author        : Wisnu Pramadhitya R
Created       : October 04 - Oktober 14, 2016
Description   : aplikasi Kalender sederhana yang mampu menampilkan kalender 
per bulan sesuai bulan dan tahun yang dimasukkan oleh pengguna.
"""
import sys

mdays = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
def leapyear(year):
    'Return True if leap year, False if not.'
    return year % 4 == 0 and (year % 100 != 0 or year % 400 == 0)

def getWeekday(year):
    'Return calculation of total days from 1800-year\nday: int total days\nyear: int 1800-2099\n'
    day = 0
    for i in range(1800, year):
        if (leapyear(i)): day += 1 # +1 if the year is leap year
        day += 365
    return day

def getMonthday(year, month):
    'Return calculation of total days from 1 January -> month\nday: \
    int total days\nyear = int 1800-2099\nmonth: int 1-12'
    day = 0
    for i in range(1, month):
        if (i == 2 and leapyear(year)): day += 29 # +29 if the year is leap year and february
        else: day += mdays[i]
    return day

def calendar(year, month):
    'Return a simple Calendar as a text that already formatted' 
    nmonths = ('', 'Januari', 'Februari', 'Maret', 'April', 'Mei', 'Juni', \
            'Juli', 'Agustus', 'September', 'Oktober', 'November', 'Desember')

    if (leapyear(year)): mdays[2] = 29 
        # change the day to 29 on index mdays[2] means February if user input is leap year
    startday = (3 + getWeekday(year) + getMonthday(year, month)) % 7
        # calculate the initial day of a month
        # +3 is normalization from 1 january 1800, that was Wednesday (3)
    
    print('{} {}\n  M    S    S    R    K    J    S'.format(nmonths[month],year))
    for i in range(startday):
        print('    ', end=' ')
    for day in range(1, mdays[month]+1):
        print('{:3}'.format(day), end='  ')
        if ((day + startday) % 7 == 0 or day == mdays[month]): print()

def main():
    'main program'
    month, year = 0, 0
    while True:
        try:
            month = int(input('masukkan bulan 1-12 (-1 untuk keluar): '))
            if (month == -1): sys.exit()
            elif not (1 <= month <= 12): 
                print('Perhatian: input bulan 1-12, selain itu, error. Coba lagi!'); continue 
                # raise error
            year = int(input('masukkan tahun: '))
            if not (1800 <= year <= 2099): 
                print('Perhatian: input tahun 1800-2099, selain itu, error. Coba lagi!'); continue 
                # raise error
            
            calendar(year,month) # display calendar
        except (ValueError): print('ValueError: input harus berupa <int>, selain itu, error. Coba lagi!') 
        # raise error /ValueError/
    
if __name__ == "__main__":
    #main()
    calendar(2016,2)