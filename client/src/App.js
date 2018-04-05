import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            projects: [],
            data : [],
            a : null
        };
        this.updateState = this.updateState.bind(this);

    }

    componentDidMount() {
        axios.get('http://localhost:9191/project')
            .then(res => {
                this.setState({ projects: res.data });
                console.log(this.state.projects);

            });
    }

    updateState(e) {

        this.setState({ a : e.target.value});
        console.log(this.state.a);

        axios.get('http://localhost:9797/project/updateStatus/'+this.state.a)
            .then(res=> {
                 this.setState({ data : res.data });
                  console.log(this.state.projects);

            });    }

    render() {
        return (
            <div className="container">
              <div className="panel panel-default">
                <div className="panel-heading">
                  <h3 className="panel-title">
                    PROJECTS LIST
                  </h3>
                </div>
                <div className="panel-body">
                  <h4><Link to="/create"><span className="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Add Project</Link></h4>
                  <table className="table table-stripe">
                    <thead>
                    <tr>
                        <th>ProjectName</th>
                        <th>Status</th>
                        <th>lastUpdated</th>
                        <th>Tasks</th>
                        <th>Review</th>

                    </tr>
                    </thead>
                    <tbody>
                    {this.state.projects.map(proj =>
                        <tr>
                            <td><Link to={`/show/${proj.id}`}>{proj.name}</Link></td>
                            {/*<td>*/}
                                {/**/}
                            {/*</td>*/}
                            {/*<td><button value={proj.id} onClick={this.updateState}>{proj.state ? <h1>Start Work</h1> : <h1>Pause</h1> }</button></td>*/}

                          <td><select name={proj.name} value={proj.id} onChange={this.updateState}>
                                <option value={proj.id}>Start Work</option>
                                <option value={proj.id}>Pause</option>
                          </select> </td>

                            <td><Link to={`/TaskComponents/`}>Tasks</Link></td>
                        </tr>
                    )}
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
        );
    }
}

export default App;
