import React, { useState } from "react";
import Question from "../../models/Question";
import ListController from "../../controllers/ListController";
import styled from "styled-components";
import { FaCheck, FaPlus, FaTrash, FaAngleUp, FaAngleDown } from 'react-icons/fa';

export default function QuestionForm({ question, setQuestion }) {
  function handleChangeText(e) {
    setQuestion(question.merge({ questionText: e.target.value }));
  }
  function handleChangeMarks(e) {
    setQuestion(question.merge({ marks: e.target.value }));
  }

  function handleChangeType(e) {
    setQuestion(question.merge({ type: e.target.value }));
  }

  function setOptions(options) {
    setQuestion(question.merge({ options }));
  }

  const listController = new ListController(question.options, setOptions);

  return (
    <div>
      <label>Question Text:</label>
      <input type="text" value={question.questionText} onChange={handleChangeText} />
      <label>Total Marks for the Question:</label>
      <input type="text" value={question.marks} onChange={handleChangeMarks} />
      <label htmlFor="question-type">Question Type:</label>
      <select
        id="question-type"
        value={question.type}
        onChange={handleChangeType}
      >
        {Object.values(Question.TYPES).map(type => (
          <option key={type} value={type}>
            {type}
          </option>
        ))}
      </select>

      {question.hasOptions && (
        <fieldset>
          <legend>Options</legend>

          {question.options.map((option, i) => (
            <Option key={i}>
              <input
                type="text"
                placeholder="Enter option"
                name={option}
                value={option}
                onChange={e => listController.set(i, e.target.value)}
              />
              <Buttons>
                <Button onClick={() => listController.moveUp(i)}>
                  <FaAngleUp />
                  </Button>
                
                
                <Button onClick={() => listController.moveDown(i)}>
                <FaAngleDown />
                </Button>
                <Button onClick={() => listController.remove(i)}>
                  <FaTrash />
                </Button>

                <Button onClick={() => listController.remove(i)}>
                  <FaCheck />
                </Button>

              </Buttons>
            </Option>
          ))}
          <p>
            <Button onClick={() => listController.add("")}>
              <FaPlus />
              Add Option
            </Button>
          </p>
        </fieldset>
      )}
    </div>
  );
}

const Option = styled.div`
  display: flex;
`

const Buttons = styled.div`
  display: flex;
  justify-content: flex-end;
`;

const Button = styled.button`
  background: none;
  color: #0366ee;
  margin-left: 0.2em;
`;
