# Linux Command line cheatsheet

### Table of contents

- [directory discovery](#directory-discovery)
  - ls
  - mkdir - rmdir
  - rm - mv - cp
  - du
  - file
  - find
- [text manipulation](#text-manipulation)
  - cat
  - head - tail
  - more - less
  - cut - awk
  - wc
  - sort
  - grep
- [user and permission](#user-and-permission)
- [others](#others)
  - history
  - date - cal
  - gzip - bzip2 - tar
  - apt

## directory discovery

```commandline
ls -ltrh
```

| option | meaning                            |
|--------|------------------------------------|
| `-t`   | sort by time                       |
| `-l`   | list                               |
| `-r`   | reverse sort                       |
| `-h`   | human readable                     |
| `-a`   | all                                |
| `-R`   | recursively (show sub directories) |

```commandline
mkdir -p dir1/dir2 dir3 dir4/dir5/dir6
rmdir -p dir4/dir5/dir6
```

| option | meaning       |
|--------|---------------|
| `-p`   | handle parent |

```commandline
rm -rf fileName
mv -r from to
cp -rv from to > logFileName
```

| option | meaning                              |
|--------|--------------------------------------|
| `-r`   | recursively (handle sub directories) |
| `-f`   | force                                |
| `-v`   | verbose (with log)                   |

```commandline
du -ch
```

| option   | meaning        |
|----------|----------------|
| `-h`     | human readable |
| `-s`     | summarize      |
| `-c`     | show total     |
| `-a`     | all            |
| `--time` | with time      |

```commandline
file fileName
```

| option | meaning |
|--------|---------|
| `-b`   | brief   |

```commandline
find dir searchConditions Operations

find . -name "?*[0-9].sh" -type f -size +1M
find . -type d -name "tmp" -exec ls -lh {} \;
find . -type d -exec du -sh {} +
find . -type d -name "make" -exec touch {}/info.txt \;
```

| option                             | meaning                      |
|------------------------------------|------------------------------|
| `-name`                            | search file name             |
| `-type`                            | search file type (d or f)    |
| `-maxdepth`, `-mindepth`           | set search depth             |
| `-empty`                           | search empty dirs and files  |
| '-size'                            | search by size               |
| `-exec` operation `{}` `+` or `\;` | execute on the search result |
| `-delete`                          | delete search results        |

## text manipulation

```commandline
cat -nb
cat > fileName

cat <<EOF > fileName
some text
EOF
```

| option | meaning        |
|--------|----------------|
| `-n`   | number line    |
| `-b`   | show non blank |

```commandline
head -n to
tail +from 
cat fileName | head -n to | tail +from
```

| option | meaning            |
|--------|--------------------|
| `-n`   | first/last n lines |
| `-c`   | first/last c chars |

```commandline
less +lineNumber fileName
```

| option         | meaning                |
|----------------|------------------------|
| `+`line number | show after line number |
| `-N`           | show number lines      |
| `-s`           | squeeze blank lines    |

| action    | meaning               |
|-----------|-----------------------|
| `space`   | next page             |
| `enter`   | next line             |
| `b`       | prev page             |
| `q`       | quit                  |
| `=`       | line number           |
| `g`, `G`  | first page, last page |
| `:`number | go to lines ahead     |

```commandline
cut -d "delimiter" -f columnNumbers fileName

awk -F delimiter '{print $1, $2}' columnarFile
awk '/regex pattern/{action}' my_file
```

| cut option           | meaning       |
|----------------------|---------------|
| `-b`                 | select bytes  |
| `d`                  | delimiter     |
| `-f`                 | select fields |
| `--output-delimiter` ||

```commandline
wc fileName
```

| option | meaning     |
|--------|-------------|
| `-l`   | lines count |
| `-w`   | words count |
| `-m`   | chars count |
| `-c`   | bytes count |

```commandline
sort -nr
```

| option | meaning       |
|--------|---------------|
| `-n`   | numeric sort  |
| `-r`   | reverse       |
| `-k`   | sort a column |

```commandline
grep string fileName
grep workd -E "word1" -E "word2" -E "word3" fileName > outplutFile
```

| option | meaning          |
|--------|------------------|
| `-i`   | case insensitive |
| `-w`   | search words     |
| `-v`   | inverse match    |
| `-c`   | count lines      |
| `-n`   | show number line |
| `-E`   | regex search     |

## user and permission

## others

```commandline
history <number>
```

| option | meaning |
|--------|---------|
| `-c`   | clear   |

- help `commandName --help`
- info `info commandName`
- manual `man commandName`

```commandline
date -u "+%Y/%m/%d"
date "+%A %B %d %T"
```

| option | meaning        |
|--------|----------------|
| `-u`   | universal time |

| format     | meaning          | format | meaning              |
|------------|------------------|--------|----------------------|
| `%D`       | mm/dd/yy         | `%T`   | HH:MM:SS             |
| `%d`       | day on month     | `%m`   | month number         |
| `%a`       | week name (Sat)  | `%A`   | week name (Saturday) |
| `%h`, `%b` | month name (Jan) | `%B`   | month name (January) |
| `%y`       | year (23)        | `%Y`   | year (2023)          |
| `%H`       | hour             | `%M`   | minute               |
| `%S`       | second           |

```commandline
cal -y -A 1 -B 2
cal 04 2022
```

| option | meaning |
|--------|---------|
| `-y`   | year    |
| `-A`   | after   |
| `-B`   | before  |

```commandline
gzip largefile
gunzip largefile.gz

bzip2 largefile
bunzip2 largefile.bz2
```

- make archive file `tar -cvf archive.tar files folders`
- ls archive file `tar -tf archive.tar`
- extract archive file `tar -xvf archive.tar`
- make compressed archive file `tar -zcvf archive.tar.gz files folders`
- extract compressed archive file `tar -zxvf archive.tar.gz`
- add new files to archive `tar -uvf backup.tar newFiles`


| option | meaning            |
|--------|--------------------|
| `-z`   | gzip               |
| `-c`   | create             |
| `-x`   | extract            |
| `-u`   | update             |
| `-v`   | verbose (with log) |
| `-f`   | get file name      |
| `-t`   | ls in archive      |

- update package list `sudo apt search zsh`
- search for a package `sudo apt search zsh`
- install a package `sudo apt install zsh`
- uninstall a package `sudo apt remove zsh`
- uninstall dependency packages which do not need anymore `sudo apt autoremove`
- upgrade installed packages `sudo apt upgrade`