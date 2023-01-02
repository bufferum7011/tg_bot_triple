start & color C
for /F "tokens=2" %%a in ('
   tasklist /FI "WindowTitle eq startBot" /FO LIST ^| findstr /B "PID:"
') do (
    taskkill /pid %%a
)

for /F "tokens=2" %%a in ('
   tasklist /FI "WindowTitle eq D:\mysql\bin\mysqld.exe" /FO LIST ^| findstr /B "PID:"
') do (
    taskkill /pid %%a
)
exit