# **Binary Operations Project**

This project provides a binary calculator with a web interface and an API service to perform various binary operations. The supported operations are addition, multiplication, AND, and OR. The binary calculator can handle binary inputs, compute results, and return the outputs via both a user-friendly web interface and a RESTful API.

---

## **Features**

- **Web Application**:
  - Perform binary addition, multiplication, AND, and OR.
  - Supports operand inputs via a web form.
  - Displays results directly on the page.
  - Edge case handling, such as zero operands.
- **Binary API Service**:
  - RESTful API for binary operations (/add_json endpoint).
  - Supports GET requests with operand1 and operand2 parameters for the supported operations.
  - Returns results in JSON format, including operand values, operator, and result.
- **Test Coverage**:
  - Unit tests for constructors and all operations.
  - Edge cases and invalid input scenarios.

---

## **Web Application Usage**
  - Open your browser and go to http://localhost:8080.
  - Enter binary numbers (e.g., 111, 1010) into the operand fields.
  - Select an operator (e.g., addition, multiplication, AND, OR).
  - Click the "=" sign to see the result displayed on the page.

---

## **API Service Usage**
  - The API is available at http://localhost:8080/add_json.
  - You can perform binary operations by making GET requests with operand1 and operand2 parameters. The response will be a JSON object.

---
