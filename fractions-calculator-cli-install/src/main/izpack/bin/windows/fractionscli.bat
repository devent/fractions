@REM
@REM Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
@REM
@REM This file is part of fractions-calculator-cli-install.
@REM
@REM fractions-calculator-cli-install is free software: you can redistribute it and/or modify it
@REM under the terms of the GNU General Public License as published by the Free
@REM Software Foundation, either version 3 of the License, or (at your option) any
@REM later version.
@REM
@REM fractions-calculator-cli-install is distributed in the hope that it will be useful, but
@REM WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
@REM FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
@REM details.
@REM
@REM You should have received a copy of the GNU General Public License along with
@REM fractions-calculator-cli-install. If not, see <http://www.gnu.org/licenses/>.
@REM

set lib="%CD%"\lib\*
set log="-Dlogback.configurationFile=file:///%CD%/etc/logback.xml"
set args=
set mainClass="${project.custom.app.mainclass}"

javaw -version >nul 2>&1 && ( set found=true ) || ( set found=false )
if %found% EQU false (
    cscript bin/windows/MessageBox.vbs "Java is not correctly installed."
    exit 1
)

start javaw %log% -cp %lib% %mainClass% %args% %*
