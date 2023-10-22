DEFAULT_IP = '127.0.0.1'
DEFAULT_PORT = 8888

def recvall(my_socket, length):
    my_bytes = bytes()
    while len(my_bytes) < length:
        my_bytes += my_socket.recv(length - len(my_bytes))
    return my_bytes


def send_number_ascii(my_socket, number):
    to_send = str(number) + " "
    my_socket.sendall(to_send.encode('ascii'))


def receive_int_ascii(my_socket):
    buffer = ""
    while True:
        my_byte = recvall(my_socket, 1)
        character = my_byte.decode('ascii')
        if character == " ":
            break
        buffer += character
    return int(buffer)


def send_int_short(my_socket, number):
    to_send = number.to_bytes(2, byteorder="big", signed=True)
    my_socket.sendall(to_send)


def receive_int_short(my_socket):
    bytes_received = recvall(my_socket, 2)
    return int.from_bytes(bytes_received, byteorder='big', signed=True)
