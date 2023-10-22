import os
import sys
import socket

from SocketProgramming.TCP.protocols import *

SERVER_IP = DEFAULT_IP
SERVER_PORT = DEFAULT_PORT


def serve(client_socket,client_address):
    print("Serving client:" + str(client_address))

    '''
    CODE GOES HERE
    '''

    print("Served client:" + str(client_address))


def main():
    try:
        print("1.Create Socket")
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    except:
        print("Eroare la crearea de socket")
        return

    try:
        print("2.Bind Socket")
        server_socket.bind((SERVER_IP, SERVER_PORT))
    except:
        print("Eroare la bind-ul socketului")
        return

    print("3.Listen")
    server_socket.listen(5)

    while (True):
        print("4.Accept")
        client = server_socket.accept()
        if (os.fork() == 0):
            try:
                serve(client[0],client[1])
            except:
                print("Eroare la I/O de informatii")
            client[0].close()
            sys.exit()


main()
